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
import com.rems.model.UserModel;

/**
 * Servlet implementation class LoginServlet
 * 
 * @name: LoginServlet
 * @author: Team Amrita Virdi
 * @description: This is a Servlet Class which takes the username and password
 *               from the calling form on the JSP, validates and checks the
 *               username and password in the database and returns to the
 *               calling Jsp in case of the error or returns to the user landing
 *               page.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		try {
			if (uname.trim().equals("")) {
				throw new CommonExceptions("Please enter username");
			} else if (pass.trim().equals("")) {
				throw new CommonExceptions("Please enter password");
			} else {
				HttpSession session = request.getSession();
				UserModel usrModel = new UserModel();
				User usr = usrModel.getUserInformation(uname, pass);

				ExpensesModel expModel = new ExpensesModel();

				List<Expenses> expList = expModel.getAllCommonExpenses(usr);
				expList.addAll(expModel.getAllExpenses(usr));

				session.setAttribute("ALLEXPENSES", expList);
				session.setAttribute("LOGGEDINUSER", usr);

				RequestDispatcher rd = request
						.getRequestDispatcher("/userLandingPage.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Exception.....");
			request.setAttribute("uname", uname);
			request.setAttribute("pass", pass);
			request.setAttribute("message", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}
}