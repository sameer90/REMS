package com.rems.data;

/**
 * @name: Expenses
 * @author: Team Amrita Virdi
 * @description: This is a bean class for Expense Table which contains a default
 *               constructor, a parameterized constructor.
 **/
public class Expenses implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer expensesId = 0;
	private Integer userId = 0;
	private Integer roomId = 0;
	private String itemDesciption;
	private String costInvolved;
	private String expenseType;

	public Expenses() {
	}

	public Expenses(int usrId, int roomId, String itemDesciption,
			String costInvolved, String expType) {
		this.roomId = roomId;
		this.userId = usrId;
		this.itemDesciption = itemDesciption;
		this.costInvolved = costInvolved;
		this.expenseType = expType;
	}

	/**
	 * @return the expensesId
	 */
	public Integer getExpensesId() {
		return expensesId;
	}

	/**
	 * @param expensesIdthe
	 *            expensesId to set
	 */
	public void setExpensesId(Integer expensesId) {
		this.expensesId = expensesId;
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
	 * @return the itemDesciption
	 */
	public String getItemDesciption() {
		return itemDesciption;
	}

	/**
	 * @param itemDesciption
	 *            the itemDesciption to set
	 */
	public void setItemDesciption(String itemDesciption) {
		this.itemDesciption = itemDesciption;
	}

	/**
	 * @return the costInvolved
	 */
	public String getCostInvolved() {
		return costInvolved;
	}

	/**
	 * @param costInvolved
	 *            the costInvolved to set
	 */
	public void setCostInvolved(String costInvolved) {
		this.costInvolved = costInvolved;
	}

	/**
	 * @return the expenseType
	 */
	public String getExpenseType() {
		return expenseType;
	}

	/**
	 * @param expenseType
	 *            the expenseType to set
	 */
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
}