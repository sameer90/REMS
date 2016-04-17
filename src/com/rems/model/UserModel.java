package com.rems.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.rems.Exceptions.CommonExceptions;
import com.rems.connection.ConnectionPool;
import com.rems.connection.PoolBean;
import com.rems.data.User;
import com.rems.util.REMSLibrary;

/**
 * @name: UsesrModel
 * @author: Team Amrita Virdi
 * @description: This is a Service class for Users and contains all the CRUD
 *               functions of the database.
 **/
public class UserModel {
	Logger logger = Logger.getLogger(ExpensesModel.class);
	Connection conn = null;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	int c = 0;

	/**
	 * 
	 * @method : getUserInfo
	 * @inputs : Integer
	 * @return : User
	 * @detail : This is method that returns a particular user information on
	 *         the basis of User id from the database.
	 * */
	public User getUserInfo(int user_id) throws CommonExceptions {
		User usr = null;
		logger.info("executing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT * from user");
			stringBuilder.append("	WHERE user_id= ");
			stringBuilder.append(user_id);
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				usr = new User();
				usr.setUserId(rs.getInt(1));
				usr.setFirstName(REMSLibrary.checkNull(rs.getString(2)));
				usr.setLastName(REMSLibrary.checkNull(rs.getString(3)));
				usr.setUserName(REMSLibrary.checkNull(rs.getString(4)));
				usr.setPassword(REMSLibrary.checkNull(rs.getString(5)));
				usr.setRoomId(rs.getInt(6));
				usr.setTotalExpense(REMSLibrary.checkNull(rs.getString(7)));
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return usr;
	}

	/**
	 * 
	 * @method : getMaxRoomId
	 * @inputs : None
	 * @return : Integer
	 * @detail : This is method that returns the maximum primary key of the Room
	 *         table from the database.
	 * */
	public int getMaxRoomId() throws CommonExceptions {
		int room_id = 0;
		logger.info("executing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT MAX(room_id) FROM room");
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				room_id = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return 0;
		} finally {
			closeConn();
		}

		return room_id;
	}

	/**
	 * 
	 * @method : getUserInformation
	 * @inputs : String,String
	 * @return : User
	 * @detail : This method returns an User bean object of particular user on
	 *         the basis of username and password and used for login purpose..
	 * */
	public User getUserInformation(String userName, String password)
			throws CommonExceptions {
		User usr = null;
		logger.info("executing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT * from user");
			stringBuilder.append("	WHERE user_name='");
			stringBuilder.append(userName);
			stringBuilder.append("'	AND password='");
			stringBuilder.append(password + "'");
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				usr = new User();
				usr.setUserId(rs.getInt(1));
				usr.setFirstName(REMSLibrary.checkNull(rs.getString(2)));
				usr.setLastName(REMSLibrary.checkNull(rs.getString(3)));
				usr.setUserName(REMSLibrary.checkNull(rs.getString(4)));
				usr.setPassword(REMSLibrary.checkNull(rs.getString(5)));
				usr.setRoomId(rs.getInt(6));
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return usr;
	}

	/**
	 * 
	 * @method : addUser
	 * @inputs : User
	 * @return : User
	 * @detail : This method inserts a new User to the database and also creates
	 *         a new Room for the new Register user.
	 * */
	public User addUser(User usr) throws CommonExceptions {
		logger.info("exectuing method.....");
		try {
			conn = REMSLibrary.getDBConnection();

			int newRoomId = getMaxRoomId() + 1;
			String sql = "INSERT INTO room (roomDescription) VALUES (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, REMSLibrary.checkNull("ROOM" + newRoomId));
			pstmt.executeUpdate();

			usr.setRoomId(newRoomId);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("INSERT INTO user (first_name,last_name,user_name,password,room_id)");
			stringBuilder.append(" VALUES(?,?,?,?,?)");
			sql = stringBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usr.getFirstName());
			pstmt.setString(2, usr.getLastName());
			pstmt.setString(3, usr.getUserName());
			pstmt.setString(4, usr.getPassword());
			pstmt.setInt(5, usr.getRoomId());
			pstmt.executeUpdate();

			sql = "Select @@Identity";
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				usr.setUserId(rs.getInt(1));

		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return usr;
	}

	/**
	 * 
	 * @method : addRoomie
	 * @inputs : User
	 * @return : User
	 * @detail : This method adds a new roomie in USER table for a particular
	 *         room.
	 * */
	public User addRoomie(User usr) throws CommonExceptions {
		logger.info("exectuing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("INSERT INTO user (first_name,last_name,user_name,password,room_id)");
			stringBuilder.append(" VALUES(?,?,?,?,?)");
			String sql = stringBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usr.getFirstName());
			pstmt.setString(2, usr.getLastName());
			pstmt.setString(3, usr.getUserName());
			pstmt.setString(4, usr.getPassword());
			pstmt.setInt(5, usr.getRoomId());
			pstmt.executeUpdate();

			sql = "Select @@Identity";
			rs = st.executeQuery(sql);
			if (rs.next())
				usr.setUserId(rs.getInt(1));
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return usr;
	}

	/**
	 * 
	 * @method : getAllRoomie
	 * @inputs : Integer, Integer
	 * @return : List<User>
	 * @detail : This method gets all the Roommates for the database and returns
	 *         a list to the calling function.
	 * */
	public List<User> getAllRoomie(int userId, int roomId)
			throws CommonExceptions {
		List<User> userList = new ArrayList<User>();
		logger.info("executing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT * FROM USER u WHERE u.room_id = ");
			stringBuilder.append(roomId);
			stringBuilder.append(" AND u.user_id <> ");
			stringBuilder.append(userId);
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User usr = new User();
				usr.setUserId(rs.getInt(1));
				usr.setFirstName(REMSLibrary.checkNull(rs.getString(2)));
				usr.setLastName(REMSLibrary.checkNull(rs.getString(3)));
				usr.setUserName(REMSLibrary.checkNull(rs.getString(4)));
				usr.setPassword(REMSLibrary.checkNull(rs.getString(5)));
				usr.setRoomId(rs.getInt(6));
				userList.add(usr);
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return userList;
	}

	/**
	 * 
	 * @method : closeConn
	 * @inputs : None
	 * @return : None
	 * @detail : It closes the active connection from the pool.
	 * */
	public void closeConn() {
		try {
			if (conn != null) {
				ConnectionPool conPool = null;
				conPool = PoolBean.getPoolObject();
				conPool.free(conn);
			}
			if (st != null) {
				st.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e2) {
			// TODO: handle exception
			logger.error("Exception in method close connection error.....", e2);
		}
	}
}