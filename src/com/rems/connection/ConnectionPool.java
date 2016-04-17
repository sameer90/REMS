package com.rems.connection;
/**
 * @name: ConnectionPool
 * @author: Team Amrita Virdi
 * @description: This is a Connection Pooling class to handle all the database connections.
 **/
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;
import com.rems.util.REMSProperties;

public class ConnectionPool implements Runnable {

	Logger logger = Logger.getLogger(ConnectionPool.class);
	
	private String driver = "";
	private String url = "";
	private String username = "";
	private String password = "";
	private String Database = "";
	private String ipAddress = "";
	private int maxConnections = 0;
	private boolean waitIfBusy = false;
	private boolean TestOnReserve = false;
	private boolean Refresh = false;
	private Vector availableConnections = null;
	private Vector busyConnections = null;
	private boolean connectionPending = false;// true when any connection is in
												// wating state.
	PrintWriter pc = null;
	public int counter = 0;
	public int ConnFail = 0;
	public boolean flag = false;
	public boolean flagAdmin = false;

	/**
	 * 
	 * @method : Class parameterized Constructor
	 * @inputs : All Class Variables
	 * @return : Initializes the available connections vector
	 * @detail : This is method initializes the class variables and creates
	 *         available connection.
	 * */
	public ConnectionPool(String driver, String url, String username,
			String password, String db, String ip, int initialConnections,
			int maxConnections, boolean waitIfBusy, boolean TestOnReserve,
			boolean Refresh) throws SQLException, IOException {
		logger.info("Connectin pool Constructer call");
		try {

			this.driver = driver;
			this.url = url;
			this.username = username;
			this.password = password;
			this.Database = db;
			this.ipAddress = ip;
			this.maxConnections = maxConnections;
			this.waitIfBusy = waitIfBusy;
			this.TestOnReserve = TestOnReserve;
			this.Refresh = Refresh;

			if (initialConnections > maxConnections) {
				initialConnections = maxConnections;
			}
			// Creating Vectors for Connection.
			availableConnections = new Vector(initialConnections);
			busyConnections = new Vector();
			for (int i = 0; i < initialConnections; i++) {
				availableConnections.addElement(makeNewConnection());
			}
		} catch (Exception e) {
			logger.error("Exception in connection pool Constructer", e);
		}
	}

	/**
	 * @method : getConnection
	 * @inputs : None
	 * @return : Connection
	 * @detail : Returns a connection from the pool
	 *
	 * */

	public synchronized Connection getConnection() throws Exception {
		logger.info("Connection Poool get Connection function called");

		if (!availableConnections.isEmpty()) {
			Connection existingConnection = (Connection) availableConnections
					.firstElement();

			availableConnections.removeElementAt(0);
			if (TestOnReserve == true
					&& testConnection(existingConnection) == false) {
				makeBackgroundConnection();
				ConnFail = ConnFail + 1;
				return (getConnection());
			}
			ConnFail = 0;
			if (Refresh == true && counter > 2000) {
				int num = availableConnections.size();
				closeConnections(availableConnections);
				availableConnections = new Vector();
				for (int i = 0; i < num; i++) {
					availableConnections.addElement(makeNewConnection());
				}
				counter = 0;
			}
			busyConnections.addElement(existingConnection);
			counter = counter + 1;// Counter incremented by one
			return (existingConnection);
		} else {
			if ((totalConnections() < maxConnections) && !connectionPending) {
				makeBackgroundConnection();
			} else if (!waitIfBusy) {
				throw new SQLException("Connection limit reached");
			}
			try {
				if (totalConnections() > 10 && (!flag)) {
					flag = true;
				}
				if (totalConnections() >= maxConnections && (!flagAdmin)) {
					flagAdmin = true;
				}
				wait();
			} catch (Exception ie) {
				logger.error("Wating for Connection:" + ie);
			}
			logger.info("Details(Avalable Empty):" + toString());
			return (getConnection());
		}
	}
	/**
	 * @method : run
	 * @inputs : None
	 * @return : None
	 * @detail : Suggested but not used You can't just make a new connection in
	 *         the foreground when none are available, since this can take
	 *         several seconds with a slow network connection. Instead, start a
	 *         thread that establishes a new connection, then wait. You get
	 *         woken up either when the new connection is established or if
	 *         someone finishes with an existing connection.
	 * 
	 * */
	private void makeBackgroundConnection() {
		connectionPending = true;
		try {
			Thread connectThread = new Thread(this);
			connectThread.start();
		} catch (OutOfMemoryError oome) {
			logger.error("Error in makeBackgroundConnection" + oome);
		}
	}

	/**
	 * @method : run
	 * @inputs : None
	 * @return : None
	 * @detail : This is a thread method to create connections at the
	 *         background.
	 * */
	public void run() {
		try {
			int counter = 0;

			if (counter >= 10) {
				return;
			}
			Connection connection = makeNewConnection();
			if (connection != null) {
				logger.info("New Connection Cerated.");

				synchronized (this) {
					availableConnections.addElement(connection);
					connectionPending = false;
					notifyAll();
				}
			} else {
				logger.info("New Connection Not Cerated.");
				counter = counter + 1;
				run();

			}
		} catch (Exception e) {
			logger.error("Exception in RUN method: " + e);
		}
	}

	/**
	 * @method : makeNewConnection
	 * @inputs : None
	 * @return : Connection
	 * @detail : This explicitly makes a new connection. Called in the
	 *         foreground when initializing the ConnectionPool, and called in
	 *         the background when running.
	 * */

	private Connection makeNewConnection() throws SQLException {
		try {

			Properties properties = new Properties();
			properties.put("user", REMSProperties.user);

			properties.put("password", REMSProperties.password);

			properties.put("db", REMSProperties.dbname);

			/* properties.put("server", AIMProperties.ipaddress); */

			Class.forName(REMSProperties.driverClass).newInstance();
			Connection connection = DriverManager.getConnection(
					REMSProperties.url, REMSProperties.user,
					REMSProperties.password);

			if (connection == null) {
				logger.error("makenewConnection Generating null Connection--------------------");
			}
			return (connection);

		} catch (Exception e) {
			logger.error("Exception In make new connection:" + e);

			throw new SQLException("Can't find class for driver: " + driver);
		}
	}

	/**
	 * @method : free
	 * @inputs : Connection
	 * @return : None
	 * @detail : Getting Connection back in the Pool.
	 * */
	public synchronized void free(Connection connection) {
		try {
			busyConnections.removeElement(connection);
			if (connection.isClosed()) {
				connection = makeNewConnection();
			}
			availableConnections.addElement(connection);
			notifyAll();
		} catch (Exception e) {
			logger.error("Exception in Free method" + e);
		}
	}

	/**
	 * @method : totalConnections
	 * @inputs : None
	 * @return : Integer
	 * @detail : It will return Total Connection
	 * */

	public synchronized int totalConnections() {
		return (availableConnections.size() + busyConnections.size());
	}

	/**
	 * @method : closeAllConnections
	 * @inputs : None
	 * @return : None
	 * @detail : Use with caution: be sure no connections are in use before
	 *         calling. Note that you are not <I>required</I> to call this when
	 *         done with a ConnectionPool, since connections are guaranteed to
	 *         be closed when garbage collected. But this method gives more
	 *         control regarding when the connections are closed.
	 * */
	public synchronized void closeAllConnections() {
		closeConnections(availableConnections);
		availableConnections = new Vector();
		closeConnections(busyConnections);
		busyConnections = new Vector();
		logger.info("All Connection Closed....");
	}

	/**
	 * @method : closeConnections
	 * @inputs : Vector
	 * @return : None
	 * @detail : This method will close single connection
	 * */

	private void closeConnections(Vector connections) {
		try {
			for (int i = 0; i < connections.size(); i++) {
				Connection connection = (Connection) connections.elementAt(i);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		} catch (SQLException sqle) {
			logger.error("Exception in close conn's: " + sqle);
		}
	}

	/**
	 * @method : toString
	 * @inputs : None
	 * @return : String
	 * @detail : This method returns the info about the connections in the pool.
	 * */

	public synchronized String toString() {
		String info = " available=" + availableConnections.size() + ", busy="
				+ busyConnections.size();
		return (info);
	}

	/**
	 * @method : testConnection
	 * @inputs : Connection
	 * @return : boolean
	 * @detail : This method test the connection.
	 * */

	public boolean testConnection(Connection con) {
		try {

			Statement stmt = con.createStatement();

			stmt.executeQuery("Select curdate()");

			return true;
		} catch (Exception e) {
			logger.error("Exception in test connection" + e);
			return false;
		}
	}

}