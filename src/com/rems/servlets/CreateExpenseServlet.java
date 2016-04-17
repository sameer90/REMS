package com.rems.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rems.Exceptions.CommonExceptions;
import com.rems.data.Expenses;
import com.rems.data.User;
import com.rems.model.ExpensesModel;

/**
 * Servlet implementation class CreateExpenseServlet
 * 
 * @name: CreateExpenseServlet
 * @author: Team Amrita Virdi
 * @description: This is a Servlet Class which takes the input from the calling
 *               form on the JSP, validates and creates an expense in the
 *               database.
 */
@WebServlet("/CreateExpenseServlet")
public class CreateExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateExpenseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String itemName = request.getParameter("itemName");
		String moneySpent = request.getParameter("moneySpent");
		String expType = request.getParameter("expType");
		String expenseUpdateId = request.getParameter("expenseUpdateId");
		String type = request.getParameter("type");
		try {

			if (itemName != null && itemName.trim().equals("")) {
				throw new CommonExceptions("Please enter Item Name");
			} else if (moneySpent != null && moneySpent.trim().equals("")) {
				throw new CommonExceptions("Please enter Money Spent");
			} else {
				ExpensesModel expModel = new ExpensesModel();
				User usr = (User) session.getAttribute("LOGGEDINUSER");
				if (type == null) {
					Expenses exp = new Expenses(usr.getUserId(),
							usr.getRoomId(), itemName, moneySpent, expType);
					exp.setExpensesId(Integer.parseInt(expenseUpdateId));
					if (expenseUpdateId.equals("0"))
						exp = expModel.addExpenses(exp);
					if (!expenseUpdateId.equals("0"))
						exp = expModel.updateExpenses(exp);
				} else {
					expModel.deleteExpenses(Integer
							.parseInt(expenseUpdateId));
				}

				List<Expenses> expList = expModel.getAllCommonExpenses(usr);
				expList.addAll(expModel.getAllExpenses(usr));

				session.setAttribute("ALLEXPENSES", expList);
				RequestDispatcher rd = request
						.getRequestDispatcher("/userLandingPage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("itemName", itemName);
			request.setAttribute("moneySpent", moneySpent);
			request.setAttribute("expType", expType);
			request.setAttribute("message", e.getMessage());
			RequestDispatcher rd = request
					.getRequestDispatcher("/userLandingPage.jsp");
			rd.forward(request, response);
		}

	}

}
