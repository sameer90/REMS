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

import com.rems.Exceptions.CommonExceptions;
import com.rems.data.User;
import com.rems.model.UserModel;

/**
 * Servlet implementation class RegisterUser
 * 
 * @name: RegisterUser
 * @author: Team Amrita Virdi
 * @description: This is a Servlet Class which takes the inputs from the
 *               register.jsp page, validates the information and creates a new
 *               user in the system or returns back to calling jsp.
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
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
		Logger logger = Logger.getLogger(RegisterUser.class);
		logger.info("Entering RegisterUser Servlet Class doPost Method");
		// TODO Auto-generated method stub
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String typeOfPage = request.getParameter("typeOfPage");
		String page = "/register.jsp";
		try {
			if (fname.trim().equals("")) {
				throw new CommonExceptions("Please enter first name");
			} else if (lname.trim().equals("")) {
				throw new CommonExceptions("Please enter last name");
			} else if (uname.trim().equals("")) {
				throw new CommonExceptions("Please enter username");
			} else if (pass.trim().equals("")) {
				throw new CommonExceptions("Please enter password");
			} else {
				User usr = new User(fname, lname, uname, pass);
				UserModel usrModel = new UserModel();
				if (typeOfPage == null) {
					usrModel.addUser(usr);
					RequestDispatcher rd = request
							.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				} else {
					page = "/createEditRoomie.jsp";

					HttpSession session = request.getSession();
					User usersession = (User) session
							.getAttribute("LOGGEDINUSER");

					usr.setRoomId(usersession.getRoomId());
					usrModel.addRoomie(usr);

					List<User> romieList = usrModel.getAllRoomie(
							usersession.getUserId(), usersession.getRoomId());

					session.setAttribute("ROOMIELIST", romieList);
					RequestDispatcher rd = request
							.getRequestDispatcher("/createEditRoomie.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in RegisterUser Servlet Class doPost Method", e);
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("uname", uname);
			request.setAttribute("pass", pass);
			request.setAttribute("message", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}