package com.rems.data;

/**
 * @name: User
 * @author: Sameer Sharma
 * @description: This is a bean class for User Table which contains a default
 *               constructor, a parameterized function and class variables that
 *               correspond to table columns.
 **/
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId = 0;
	private Integer roomId = 0;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	public String totalExpense;

	public User() {
	}

	public User(String firstName, String lastName, String userName,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the totalExpense
	 */
	public String getTotalExpense() {
		return totalExpense;
	}

	/**
	 * @param totalExpense
	 *            the totalExpense to set
	 */
	public void setTotalExpense(String totalExpense) {
		this.totalExpense = totalExpense;
	}
}