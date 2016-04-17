<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>REMS Calculate Expenses</title>


<link href="${pageContext.request.contextPath}/css/userlandingpage.css"
	rel="stylesheet" type="text/css" />

</head>

<body 
	background="${pageContext.request.contextPath}/images/background.jpg">
<div id="head" align="center">
		<img src="${pageContext.request.contextPath}/images/logo.png"
			width="747" height="130" alt="" />
	</div>
	<table width="70%" border="0" align="left" cellpadding="0" id="table"
		height="100%" cellspacing="0">
		<tr>
			<td align="center" style="border: 0px solid; border-top: none;"
				valign="middle">
				<div
					style="float: left; height: 400px; width: 22%; border-right: 0px solid;">
					<form id="butForm" method="post"
						action="${pageContext.request.contextPath}/CommonServlet">
						<table width="100%" border="0" style="font-size: 18px;"
							align="center" cellspacing="5" cellpadding="0">
							<tr>
								<td valign="top" height="auto"><input type="submit"
									name="home" id="expense"
									style="cursor: pointer; color: #000; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
									value="My Expenses"></td>
							</tr>
							<tr>
								<td valign="top"><input type="submit" name="alterRoomie"
									id="update"
									style="color: #000; cursor: pointer; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
									value="Create/Update Roomie"></td>
							</tr>

							<tr>
								<td valign="top"><input type="button" name="calcExpenses"
									id="calc"
									style="color: #000;background-color: aqua; cursor: none; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
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
						<div style="float: right;">
							<a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
						</div>
						            <div style="float: center;font-weight: bold;">Welcome, ${sessionScope.LOGGEDINUSER.firstName}
										${sessionScope.LOGGEDINUSER.lastName}					
									</div>
					</div>
					<div id="">
								<div id="calcExpenses">
									<div style="padding-bottom: 10px;">

										<div
											style="overflow: scroll; padding-bottom: 10px; overflow-x: hidden; overflow-y: auto; height: 100px;">

											<table width="100%" border="0" style="color: #fff;">
												<c:set var="totalExpenses" value="0" />
												<c:set var="totalMembers" value="${TOTALMEMBERS}" />
												<c:set var="allExpenseTotal" value="0" />

												<tr>
													<th colspan="10" style="background-color: rgb(128,100,162);">Total: Personal Expenses</th>
												</tr>
												<tr>
													<th style="background-color: rgb(128,100,162);">Item/Name</th>
													<th style="background-color: rgb(128,100,162);">Cost</th>
												</tr>
												<c:set var="colour1" value="rgb(216,211,224)" />
												<c:set var="colour" value="rgb(216,211,224)" />
												<c:set var="colour2" value="rgb(237,234,240)" />
												<c:forEach var="allPersonalExpense"
													items="${sessionScope.ALLPERSONALEXPENSES}">
													<tr>
														<td style="background-color: ${colour};color: black;" align="center">${allPersonalExpense.itemDesciption}</td>
														<td style="background-color: ${colour};color: black;" align="center">${allPersonalExpense.costInvolved}</td>
													</tr>
													<!-- Alternate Colours for the Table rows -->
										<c:set var="colour1" value="${colour2}" />
										<c:set var="colour2" value="${colour}" />
										<c:set var="colour" value="${colour1}" />
													
													<c:set var="totalExpenses"
														value="${totalExpenses + allPersonalExpense.costInvolved}" />
												</c:forEach>
												<c:set var="allExpenseTotal"
													value="${allExpenseTotal+ totalExpenses}" />
												<c:set var="totalExpenses" value="0" />
											</table>
										</div>
									<br/>
										<div
											style="overflow: scroll; padding-bottom: 10px; overflow-x: hidden; overflow-y: auto; height: 100px;">

										<table width="100%" border="0" width="25%" style="color: #fff;">
											<tr>
												<th colspan="10" style="background-color: rgb(247,150,70);">Total: Common Expenses</th>
											</tr>
											<tr>
												<th style="background-color: rgb(247,150,70);">Item Name</th>
												<th style="background-color: rgb(247,150,70);">Cost</th>
											</tr>
											<c:set var="colour1" value="rgb(252,221,207)" />
												<c:set var="colour" value="rgb(252,221,207)" />
												<c:set var="colour2" value="rgb(253,239,233)" />
											<c:forEach var="allCommonExpense"
												items="${sessionScope.ALLCOMMONEXPENSES}">
												<tr>
													<td align="right" style="color: black;background-color: ${colour};">${allCommonExpense.itemDesciption}</td>
													<td align="right" style="color: black;background-color: ${colour};">${allCommonExpense.costInvolved}</td>
												</tr>
												<c:set var="totalExpenses"
													value="${totalExpenses + allCommonExpense.costInvolved}" />
										
										<!-- Alternate Colours for the Table rows -->
										<c:set var="colour1" value="${colour2}" />
										<c:set var="colour2" value="${colour}" />
										<c:set var="colour" value="${colour1}" />
											</c:forEach>
											<c:set var="allExpenseTotal"
												value="${allExpenseTotal+ totalExpenses}" />

											<tr>
												<td align="right" style="background-color: rgb(247,150,70);">Total:</td>
												<td align="right" style="background-color: rgb(247,150,70);">$${totalExpenses}</td>
											</tr>
										</table>
										</div>
										<br/>
										<table style="color: black;" align="center" width="50%" cellspacing="10" border="0">

											<tr>
												<td style="font-size: 18px; " align="right">Total
													Cost:</td>
												<td style="font-size: 18px; " align="right">$<fmt:formatNumber
														value="${allExpenseTotal}" maxFractionDigits="2" /></td>
											</tr>
											<tr>

												<td style="font-size: 18px; " align="right">Total
													Members:</td>
												<td style="font-size: 18px; " align="right">${totalMembers}</td>

											</tr>
											<tr>
												<td height="20" valign="middle" style="font-size: 18px; " align="right">Individual
													Share:</td>
												<td style="font-size: 18px;" align="right">$<fmt:formatNumber
														value="${allExpenseTotal / totalMembers}"
														maxFractionDigits="2" /></td>

											</tr>
										</table>

</div>
</div>
</div>
</div>
</td>
</tr>
</table>
	
	<div id="footer" align="center" style="margin-top: 100px;">
		<table width="734" border="0" id="footertable">
			<tbody>
				<tr>
					<td align="right"></td>
					<td align="center" colspan="3">Connect Us On</td>
					<td></td>
					<td></td>
				</tr>

				<tr>
					<td width="483" height="70" align="">. Copyright © REMS 2016</td>
					<td width="80" align="center"><a href=""><img
							src="${pageContext.request.contextPath}/images/facebook.png"
							width="46" height="44" alt="" /></a></td>
					<td width="80" align="center"><a href=""><img
							src="${pageContext.request.contextPath}/images/insta.png"
							width="53" height="44" alt="" /></a></td>
					<td width="80" align="center"><a href=""><img
							src="${pageContext.request.contextPath}/images/twitter.png"
							width="40" height="39" alt="" /></a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>