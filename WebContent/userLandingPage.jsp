<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>REMS User Dashboard</title>

<link href="${pageContext.request.contextPath}/css/userlandingpage.css"
	rel="stylesheet" type="text/css" />


<script>
	function editExpense(itemDesc,typeOfExpense,costInvolvd,expensId){
		document.getElementsByName("itemName")[0].value=itemDesc;
		document.getElementsByName("moneySpent")[0].value=costInvolvd;
		document.getElementsByName("expType")[0].value=typeOfExpense;
		document.getElementsByName("expenseUpdateId")[0].value=expensId;
		}
	function delExpense(expId){
		
		var conf = confirm('Are you sure you want to delete this expense?');
		if(conf==true)
			{
			document.getElementById('del').elements.namedItem("expenseUpdateId").value=expId;
			document.getElementById('del').submit();
			}		
		}
	</script>


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
								<td valign="top" height="auto"><input type="button"
									name="home" id="expense"
									style="color: #000; background-color: aqua; cursor: none; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
									value="My Expenses"></td>
							</tr>
							<tr>
								<td valign="top"><input type="submit" name="alterRoomie"
									id="update"
									style="color: #000; cursor: pointer; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
									value="Create/Update Roomie"></td>
							</tr>

							<tr>
								<td valign="top"><input type="submit" name="calcExpenses"
									id="calc"
									style="cursor: pointer; color: #000; margin: 0px; width: 100%; font-size: 17px; padding: 10px;"
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
						<div id="viewExpences">
							<form id="butForm" name="butForm" method="post"
								action="${pageContext.request.contextPath}/CreateExpenseServlet">
								<table width="100%" border="0" align="center" cellpadding="0">
									<tr>
										<th width="40%">Item/Group Name</th>
										<th width="12%">Expense</th>
										<th width="18%">Type of Expense</th>
										<th>Options</th>
									</tr>
									<tr>
										<td align="center"><input size="50" type="text"
											name="itemName" value="${requestScope.itemName}"></td>
										<td align="center"><input size="11"
											style="text-align: right;" type="text" name="moneySpent"
											value="${requestScope.moneySpent}"> <input
											type="hidden" name="expenseUpdateId" value="0"></td>
										<td align="center"><select name="expType"
											style="width: 100%;">
												<option value="Personal"
													<c:if test="${requestScope.expType=='Personal'}">selected="selected"</c:if>>Personal</option>
												<option value="Common"
													<c:if test="${requestScope.expType=='Common'}">selected="selected"</c:if>>Common</option>
										</select></td>
										<td width="15%"><input type="submit" name="saveBut"
											value="Save"
											style="background-color: rgb(155, 187, 89); color: white;">
											<input type="reset" name="calcelBut" value="Cancel"
											
											style="background-color: rgb(128, 100, 162); color: white;"></td>
									</tr>
								</table>
							</form>
							<hr />
							<table width="100%" border="0" align="center" cellpadding="5"
								cellspacing="1">
								<tr>
									<th width="15%" valign="top"
										style="color: white; background-color: rgb(192, 80, 77);">Commands</th>
									<th width="40%" valign="top"
										style="color: white; background-color: rgb(192, 80, 77);">Item/Group
										Name</th>
									<th width="20%" valign="top"
										style="color: white; background-color: rgb(192, 80, 77);">Expense
										Type</th>
									<th width="25%" valign="top"
										style="color: white; background-color: rgb(192, 80, 77);">Money
										Spent</th>
								</tr>
							</table>
							<div
								style="overflow: scroll; padding-bottom: 10px; overflow-x: hidden; overflow-y: auto; height: 180px;">
								<form name="del" id="del" action="${pageContext.request.contextPath}/CreateExpenseServlet" method="post">
								<table width="100%" border="0" align="left" cellpadding="5"
									cellspacing="1">
									
									<c:set var="totalExpenses" value="0" />
									<c:set var="colour1" value="rgb(208,208,208)" />
									<c:set var="colour" value="rgb(208,208,208)" />
									<c:set var="colour2" value="rgb(244,233,233)" />
									<c:forEach var="allExpenses"
										items="${sessionScope.ALLEXPENSES}">
										<tr>
											<td
												style="background-color: ${colour};border-bottom: 1px solid;"
												align="center" width="15.4%" valign="top">
												
												<a
												style="cursor: pointer; text-decoration: underline; color: black;"
												onclick="javascript: editExpense('${allExpenses.itemDesciption}',
												'${allExpenses.expenseType}','${allExpenses.costInvolved}',
												'${allExpenses.expensesId}')">Edit</a>
												&nbsp;&nbsp;												
												<a
												onclick="javascript: delExpense('${allExpenses.expensesId}');"
												style="text-decoration: underline; color: red;cursor: pointer;">Delete</a>
												
												</td>												
											<td
												style="background-color: ${colour};border-bottom: 1px solid;"
												width="41.2%" valign="top">${allExpenses.itemDesciption}</td>
											<td
												style="background-color: ${colour};border-bottom: 1px solid;"
												align="right" width="20.1%" valign="top">${allExpenses.expenseType}</td>
											<td
												style="background-color: ${colour};border-bottom: 1px solid;"
												align="right" width="25%" valign="top">${allExpenses.costInvolved}</td>

										</tr>
										<c:set var="totalExpenses"
											value="${totalExpenses + allExpenses.costInvolved}" />
										<!-- Alternate Colours for the Table rows -->
										<c:set var="colour1" value="${colour2}" />
										<c:set var="colour2" value="${colour}" />
										<c:set var="colour" value="${colour1}" />


									</c:forEach>
									
								</table>
									<input type="hidden" name="expenseUpdateId" value="0">
												<input type="hidden" name="type" value="del">
											
									</form>
							</div>

							<table
								style="border-top: 1px solid; border-bottom: 1px solid; background-color: rgb(192, 80, 77); color: white;"
								width="100%" height="30px" border="0" align="left"
								cellpadding="0" cellspacing="0">
								<tr>
									<td align="right" width="75%" style="border-right: 1px solid;"
										valign="middle">Total:</td>
									<td align="right" width="25%" style="" valign="middle">${totalExpenses}</td>

								</tr>
							</table>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<div id="footer" align="center">
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