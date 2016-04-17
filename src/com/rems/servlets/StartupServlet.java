/**
 * Servlet implementation class StartupServlet
 * 
 * @name: StartupServlet
 * @author: Team Amrita Virdi
 * @description: This is a Servlet Class starts the connection to the database and 
 * 				creates a pool of connections to be used while processing the data. This Servlet 
 * 				is called from web.xml on the startup of the application server.
 */
package com.rems.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rems.connection.ConnectionPool;
import com.rems.connection.PoolBean;
import com.rems.util.REMSLibrary;
import com.rems.util.REMSProperties;

/**
 * Servlet implementation class StartupServlet
 */
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartupServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		Logger logger = Logger.getLogger(StartupServlet.class);
		logger.info("Executing StartupServlet Servlet....-REMS");
		super.init(config);
		Connection con = null;
		super.init(config);

		try {
			ConnectionPool conPool_Admin = null;
			conPool_Admin = new ConnectionPool(REMSProperties.driverClass,
					REMSProperties.url, REMSProperties.user,
					REMSProperties.password, REMSProperties.dbname,
					REMSProperties.ip, 4, 40, true, true, true);

			PoolBean poolbean = new PoolBean();
			poolbean.setPoolObject(conPool_Admin);

			con = REMSLibrary.getDBConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Exception in StartupServlet Servlet....-REMS", e);
		} finally {
			if (con != null) {
				// freeing connection for IVM_Monitoring connectionPool
				ConnectionPool conPool = null;
				conPool = PoolBean.getPoolObject();
				conPool.free(con);

			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
