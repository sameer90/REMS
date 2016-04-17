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
import com.rems.data.Expenses;
import com.rems.data.User;
import com.rems.util.REMSLibrary;

/**
 * @name: ExpensesModel
 * @author: Team Amrita Virdi
 * @description: This is a Service class for Expenses and contains all the CRUD
 *               functions of the database.
 **/
public class ExpensesModel {
	Logger logger = Logger.getLogger(ExpensesModel.class);
	Connection conn = null;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	int c = 0;

	/**
	 * 
	 * @method : getAllExpenses
	 * @inputs : User
	 * @return : List<Expenses>
	 * @detail : This is method returns all the Expenses from the database for a
	 *         particular user.
	 * */
	public List<Expenses> getAllExpenses(User usr) throws CommonExceptions {
		logger.info("executing method.....");
		List<Expenses> expensesList = new ArrayList<Expenses>();
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT expenses_id,item_desciption,cost_involved,user_id,room_id,expenseType FROM Expenses e");
			stringBuilder.append("	WHERE e.user_id= ");
			stringBuilder.append(usr.getUserId());
			stringBuilder.append(" AND expenseType='Personal'");
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Expenses expBean = new Expenses();
				expBean.setExpensesId(rs.getInt(1));
				expBean.setItemDesciption(REMSLibrary.checkNull(rs.getString(2)));
				expBean.setCostInvolved(REMSLibrary.checkNull(rs.getString(3)));
				expBean.setUserId(rs.getInt(4));
				expBean.setRoomId(rs.getInt(5));
				expBean.setExpenseType(REMSLibrary.checkNull(rs.getString(6)));
				expensesList.add(expBean);
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return expensesList;
	}

	/**
	 * 
	 * @method : getAllCommonExpenses
	 * @inputs : User
	 * @return : List<Expenses>
	 * @detail : This is a method that returns all the Common Expenses for a
	 *         particular Room.
	 * */
	public List<Expenses> getAllCommonExpenses(User usr)
			throws CommonExceptions {
		logger.info("executing method.....");
		List<Expenses> expensesList = new ArrayList<Expenses>();
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT expenses_id,item_desciption,cost_involved,user_id,room_id,expenseType FROM Expenses e");
			stringBuilder.append("	WHERE e.room_id= ");
			stringBuilder.append(usr.getRoomId());
			stringBuilder.append(" AND expenseType='Common'");
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Expenses expBean = new Expenses();
				expBean.setExpensesId(rs.getInt(1));
				expBean.setItemDesciption(REMSLibrary.checkNull(rs.getString(2)));
				expBean.setCostInvolved(REMSLibrary.checkNull(rs.getString(3)));
				expBean.setUserId(rs.getInt(4));
				expBean.setRoomId(rs.getInt(5));
				expBean.setExpenseType(REMSLibrary.checkNull(rs.getString(6)));
				expensesList.add(expBean);
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return expensesList;
	}

	/**
	 * 
	 * @method : getAllPersonalExpenses
	 * @inputs : Integer
	 * @return : List<Expenses>
	 * @detail : This is method that gets all the personal expenses from the
	 *         database and returns a list to the calling function for a
	 *         particular room.
	 * */
	public List<Expenses> getAllPersonalExpenses(int roomId)
			throws CommonExceptions {
		logger.info("executing method.....");
		List<Expenses> expensesList = new ArrayList<Expenses>();
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT expenses_id,item_desciption,cost_involved,user_id,room_id,expenseType FROM Expenses e");
			stringBuilder.append("	WHERE e.room_id= ");
			stringBuilder.append(roomId);
			stringBuilder.append(" AND expenseType='Personal'");
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Expenses expBean = new Expenses();
				expBean.setExpensesId(rs.getInt(1));
				expBean.setItemDesciption(REMSLibrary.checkNull(rs.getString(2)));
				expBean.setCostInvolved(REMSLibrary.checkNull(rs.getString(3)));
				expBean.setUserId(rs.getInt(4));
				expBean.setRoomId(rs.getInt(5));
				expBean.setExpenseType(REMSLibrary.checkNull(rs.getString(6)));
				expensesList.add(expBean);
			}
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return expensesList;
	}

	/**
	 * 
	 * @method : getCountMembers
	 * @inputs : Integer
	 * @return : Integer
	 * @detail : This is a method returns the total number of room mates in a
	 *         particular room.
	 * */
	public Long getCountMembers(int roomId) throws CommonExceptions {
		long totalcount = 0;
		logger.info("executing method.....");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("SELECT COUNT(u.user_id) FROM USER u WHERE u.room_id="
							+ roomId);
			String sql = stringBuilder.toString();
			rs = st.executeQuery(sql);
			if (rs.next())
				totalcount = rs.getInt(1);
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return totalcount;
	}

	/**
	 * 
	 * @method : updateExpense
	 * @inputs : Expense Bean
	 * @return : Expense Bean
	 * @detail : This is a method that updates a particular Expense in the
	 *         database.
	 * */
	public Expenses updateExpenses(Expenses exp) throws CommonExceptions {
		logger.info("exectuing method.....Update Expenses");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("update expenses");
			stringBuilder
					.append("	set item_desciption=?, cost_involved=?, expenseType=? where expenses_id=?");
			String sql = stringBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exp.getItemDesciption());
			pstmt.setString(2, exp.getCostInvolved());
			pstmt.setString(3, exp.getExpenseType());
			pstmt.setInt(4, exp.getExpensesId());
			c = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return exp;
	}

	/**
	 * 
	 * @method : deleteExpense
	 * @inputs : Integer
	 * @return : Integer
	 * @detail : This is a method that deletes a particular expense from the
	 *         Expense Table in the database.
	 * */
	public int deleteExpenses(int expeId) throws CommonExceptions {
		logger.info("exectuing method.....Delete Expenses");
		int resp = 0;
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("delete from expenses where expenses_id=?");
			String sql = stringBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, expeId);
			c = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.error("Exception in method close connection error.....", e);
			return 0;
		} finally {
			closeConn();
		}
		return resp;
	}

	/**
	 * 
	 * @method : addExpense
	 * @inputs : Expense Bean
	 * @return : Expense Bean
	 * @detail : This is method that inserts a new expense into the table
	 *         expense.
	 * */
	public Expenses addExpenses(Expenses exp) throws CommonExceptions {
		logger.info("exectuing method.....addExpenses");
		try {
			conn = REMSLibrary.getDBConnection();
			st = conn.createStatement();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO expenses");
			stringBuilder
					.append("	(item_desciption,cost_involved,user_id,room_id,expenseType)");
			stringBuilder.append("	VALUES(?,?,?,?,?)");
			String sql = stringBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exp.getItemDesciption());
			pstmt.setString(2, exp.getCostInvolved());
			pstmt.setInt(3, exp.getUserId());
			pstmt.setInt(4, exp.getRoomId());
			pstmt.setString(5, exp.getExpenseType());
			c = pstmt.executeUpdate();

			sql = "Select @@Identity";
			rs = st.executeQuery(sql);
			if (rs.next())
				exp.setExpensesId(rs.getInt(1));
		} catch (Exception e) {
			logger.error("Exception in method addExpenses close connection error.....", e);
			return null;
		} finally {
			closeConn();
		}
		return exp;
	}

	/**
	 * 
	 * @method : closeConn
	 * @inputs : None
	 * @return : None
	 * @detail : It closes the active connection from the pool.
	 * */
	public void closeConn() {
		
		logger.info("Entering close connection method in Expenses Model");
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