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

import org.apache.log4j.Logger;

import com.rems.data.Expenses;
import com.rems.data.User;
import com.rems.model.ExpensesModel;
import com.rems.model.UserModel;

/**
 * Servlet implementation class CommonServlet
 * 
 * @name: CommonServlet
 * @author: Team Amrita Virdi
 * @description: This is a Servlet Class which helps to navigate through My
 *               Expenses, Create Roomie and Calculate Share buttons on the UI.
 *               This class gets the required data from the database for a
 *               particular Button Type and puts it in session scope to show the
 *               data on the JSP.
 */
@WebServlet("/CommonServlet")
public class CommonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommonServlet() {
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
		Logger logger = Logger.getLogger(CommonServlet.class);
		logger.info("Entering Common Servlet Class doPost Method");
		HttpSession session = request.getSession();
		try {
			if (request.getParameter("home") != null) {
				User usr = (User) session.getAttribute("LOGGEDINUSER");
				ExpensesModel expModel = new ExpensesModel();

				List<Expenses> expList = expModel.getAllCommonExpenses(usr);
				expList.addAll(expModel.getAllExpenses(usr));

				session.setAttribute("ALLEXPENSES", expList);
				RequestDispatcher rd = request
						.getRequestDispatcher("/userLandingPage.jsp");
				rd.forward(request, response);

			} else if (request.getParameter("alterRoomie") != null) {
				User usersession = (User) session.getAttribute("LOGGEDINUSER");

				UserModel usrModel = new UserModel();
				List<User> romieList = usrModel.getAllRoomie(
						usersession.getUserId(), usersession.getRoomId());
				session.setAttribute("ROOMIELIST", romieList);
				RequestDispatcher rd = request
						.getRequestDispatcher("/createEditRoomie.jsp");
				rd.forward(request, response);
			} else {
				User usr = (User) session.getAttribute("LOGGEDINUSER");
				ExpensesModel expModel = new ExpensesModel();
				List<Expenses> expList = expModel.getAllCommonExpenses(usr);
				List<Expenses> userPersonalExpList = expModel
						.getAllPersonalExpenses(usr.getRoomId());

				long totalMembers = expModel.getCountMembers(usr.getRoomId());

				session.setAttribute("ALLCOMMONEXPENSES", expList);
				session.setAttribute("ALLPERSONALEXPENSES", userPersonalExpList);
				session.setAttribute("TOTALMEMBERS", totalMembers);
				RequestDispatcher rd = request
						.getRequestDispatcher("/calcExpenses.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			logger.error("Exception in Common Servlet Class doPost Method", e);
		}

	}
}
