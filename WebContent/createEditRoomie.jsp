<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>REMS Roomie</title>

<link href="${pageContext.request.contextPath}/css/createEditRoomie.css"
	rel="stylesheet" type="text/css" />


</head>

<body background="${pageContext.request.contextPath}/images/background.jpg">
<div  id="head" align="center"><img src="${pageContext.request.contextPath}/images/logo.png" width="747" height="130" alt=""/></div>
	<table width="70%" border="0" align="left" cellpadding="0" id="table"
					height="100%" cellspacing="0">
					<tr>
						<td align="center" style="border: 0px solid; border-top: none;"
							valign="middle">
							<div
								style="float: left; height: 400px; width: 22%; border-right: 0px solid;">
								<form id="butForm" method="post"
									action="${pageContext.request.contextPath}/CommonServlet">
								  <table  width="100%" border="0" style="font-size: 18px;" align="center" cellspacing="5" cellpadding="0">
								    <tr>
								      <td valign="top" height="auto"><input type="submit" name="home" id="expense" 
										style="color: #000;cursor:pointer; margin:0px;width:100%;font-size: 17px;padding: 10px;"
										value="My Expenses"></td>
							        </tr>
								    <tr>
								      <td valign="top"><input type="button" name="alterRoomie"  id="update"
										style="color: #000; cursor: none;background-color: aqua;margin:0px;width:100%;font-size: 17px;padding: 10px;"
										value="Create/Update Roomie"></td>
							        </tr>
								    <tr>
								      <td valign="top"><input type="submit" name="calcExpenses" id="calc" 
										style="cursor:pointer;color: #000;margin:0px;width:100%;font-size: 17px;padding: 10px;"
										value="Calculate Share"></td>
							        </tr>
								    <tr>
								      <td valign="top">&nbsp;</td>
							        </tr>
							      </table>
								</form>
							</div>
							<div style="float: right; width: 68%;">
								<div
									style="float: left; padding: 8px; margin-bottom: 10px; width: 98%; text-align: left; border-bottom: 2px solid;">
									<div style="float: right;"><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
									</div>
									
                                    <div style="float: center;font-weight: bold;">Welcome, ${sessionScope.LOGGEDINUSER.firstName}
										${sessionScope.LOGGEDINUSER.lastName}					
									</div>
                                    
								</div>
								<div id="">
									<form id="roomieForm" method="post"
								action="${pageContext.request.contextPath}/RegisterUser">
										<table width="98%" border="0" align="center" cellpadding="0">
											<tr>
												<th >First Name</th>
												<th>Last Name</th>
												<th>User Name</th>
												<th>Password</th>
											</tr>
											<tr>
												<td>												
												<input type="hidden" name="typeOfPage" id="typeOfPage"
													value="roomie">
												<input type="text" name="fname" id="fname"
													value=""></td>
												<td><input type="text" name="lname" id="lname"
													value=""></td>
												<td><input type="text" name="uname" id="uname"
													value=""></td>
												<td><input type="text" name="pass" id="pass" value=""></td>
												<td><input type="submit" name="saveBut" value="Add"></td>
											</tr>
										</table>
									</form>
									<hr />
									<table width="100%" border="0" align="center" style="color: white;" cellpadding="5">
										<tr>
											<th style="background-color: black" width="25%" valign="top">First Name</th>
											<th style="background-color: black" width="25%" valign="top">Last Name</th>
											<th style="background-color: black" width="25%" valign="top">User Name</th>
											<th style="background-color: black" width="25%" valign="top">Password</th>
										</tr>
									</table>
									<div
										style="overflow: scroll; padding-bottom: 10px; overflow-x: hidden; 
										overflow-y: auto; height: 200px;">
										<table width="100%" border="0" align="left" cellpadding="5" style="color: white;"
									>
										
											<c:set var="colour1" value="rgb(153,62,60)" />
												<c:set var="colour" value="rgb(153,62,60)" />
												<c:set var="colour2" value="rgb(192,80,77)" />
											<c:forEach var="allRoomies" items="${sessionScope.ROOMIELIST}">
											<tr>
												<td width="20%"
													style="background-color: ${colour};border-right: 1px solid; border-bottom: 1px solid;"
													valign="top">${allRoomies.firstName}</td>
												<td
													style="border-right: 1px solid;background-color: ${colour}; border-bottom: 1px solid;"
													width="20%" valign="top">${allRoomies.lastName}</td>
												<td style="border-bottom: 1px solid;background-color: ${colour};" align="right"
													width="20%" valign="top">${allRoomies.userName}</td>
												<td style="border-bottom: 1px solid;background-color: ${colour};" align="right"
													width="20%" valign="top">${allRoomies.password}</td>
											</tr>
												<!-- Alternate Colours for the Table rows -->
										<c:set var="colour1" value="${colour2}" />
										<c:set var="colour2" value="${colour}" />
										<c:set var="colour" value="${colour1}" />
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
    <div  id="footer" align="center" > <table width="734" border="0" id="footertable">
  <tbody>
  <tr><td align="right"></td>
  <td align="center" colspan="3">Connect Us On</td>
  <td></td><td></td></tr>
  
    <tr>
      <td width="483" height="70" align=""> . Copyright © REMS 2016 </td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/facebook.png" width="46" height="44" alt=""/></a></td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/insta.png" width="53" height="44" alt=""/></a></td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/twitter.png" width="40" height="39" alt=""/></a></td>
    </tr>
  </tbody>
</table>
</div>
</body>
</html>