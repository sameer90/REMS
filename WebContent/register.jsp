<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>REMS Login</title>
<style>
#table {
	
	border-radius: 1px;
	position: absolute;
	top: 190px;
	bottom: 30%;
	width: 500px;
	height: 146px;
	left: 480px;
	
}

#head
{
	
	position: absolute;
	top: 2%;
	left: 26.25%;
	right: 31%;
	align: center;
	height: 128px;
}
#footer
{
	
	position: absolute;
	top: 80%;
	left: 26%;
	right: 30%;
	align: center;
	height: 128px;
}
#footertable
{
	background:#686565;
	position: absolute;
	top: 35%;
	left: 2px;
	right: 30%;
	align: center;
	width: 738px;
}

#signin
{

	
	left: 40px;
	
	align: center;
	
}
#head table tbody tr td h1 {
	font-family: Lucida Grande, Lucida Sans Unicode, Lucida Sans, DejaVu Sans, Verdana, sans-serif;
}
</style>
</head>

<body background="${pageContext.request.contextPath}/images/background.jpg">
<div  id="head" align="center"><img src="${pageContext.request.contextPath}/images/logo.png" width="747" height="130" alt=""/></div>
	<table width="100%" border="0" align="center" cellpadding="0"
		height="100%" cellspacing="0">
		
<tr>
			<td align="center" valign="middle">
				<table id="table" width="50%" height="250" border="0">
					<tr>
						<td class="title6" align="center" valign="top">REMS Register<br />
							<hr />
						</td>
					</tr>
					<tr>
						<td align="center" valign="top" height="30px">
							<div style="color: red; font-size: 13px;" id="loginerror">
								${requestScope.message}</div>
						</td>
					</tr>

					<tr>
						<td valign="top">
							<form id="login" method="post"
								action="${pageContext.request.contextPath}/RegisterUser">
								<table width="106%">
									<tr>
										<td class="title6" colspan="2" align="center">First Name:</td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="text" name="fname" id="fname"
											class="textarea" value="${requestScope.fname}"
											style="postion: absolute; top: 28px; left: 9px;"></td>
									</tr>

									<tr>
										<td class="title6" colspan="2" align="center">Last Name</td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="text" name="lname"
											id="lname" class="textarea" value="${requestScope.lname}"
											style="postion: absolute; top: 86px; left: 9px;"></td>
									</tr>
									<tr>
										<td class="title6" colspan="2" align="center">User Name</td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input type="text" name="uname"
											id="uname" class="textarea" value="${requestScope.uname}"
											style="postion: absolute; top: 86px; left: 9px;"></td>
									</tr>
									<tr>
										<td class="title6" colspan="2" align="center">Password</td>
									</tr>
									<tr>
										<td height="32" colspan="2" align="center"><input type="password" name="pass" value="${requestScope.pass}"
											id="pass" class="textarea"
											style="postion: absolute; top: 86px; left: 9px;"></td>
									</tr>
									<tr>
										<td width="69%" height="49" align="right">
								<input type="submit" value="" style="cursor: pointer;background-image: url('${pageContext.request.contextPath}/images/register/register-now-button.png'); background-size: 177px 38px;  width: 177px;height: 38px;border:0px;"/>
												
										</td>
                                         <td width="31%" align="left" valign="bottom">
								    <a href="index.jsp">  <img src="${pageContext.request.contextPath}/images/register/back.png" width="150" height="42" alt=""/></a></td>
									</tr>
									<tr>
										
                                     
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
        
</table>
<div  id="footer" align="center" > <table width="734" border="0" id="footertable">
  <tbody>
  <tr><td align="right"></td>
  <td align="center" colspan="3">Connect Us On</td>
  <td></td><td></td></tr>
  
    <tr>
      <td width="483" height="70" align="">.Copyright REMS 2016 </td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/facebook.png" width="46" height="44" alt=""/></a></td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/insta.png" width="53" height="44" alt=""/></a></td>
      <td width="80" align="center"><a href=""><img src="${pageContext.request.contextPath}/images/twitter.png" width="40" height="39" alt=""/></a></td>
    </tr>
  </tbody></table></div>

</body>
</html>