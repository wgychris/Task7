
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="template-top2.jsp" />

<jsp:include page="error-list.jsp" />
<style type="text/css">
#tfheader {
	background-color: #c3dfef;
}

#tfheader2 {
	background-color: #F5F5DC;
}

#tfnewsearch {
	float: right;
	padding: 20px;
}

.tftextinput {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	border: 1px solid #0076a3;
	border-right: 0px;
	border-top-left-radius: 5px 5px;
	border-bottom-left-radius: 5px 5px;
}

.tfbutton {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	color: #ffffff;
	border: solid 1px #0076a3;
	border-right: 0px;
	background: #0095cd;
	background: -webkit-gradient(linear, left top, left bottom, from(#00adee),
		to(#0078a5));
	background: -moz-linear-gradient(top, #00adee, #0078a5);
	border-top-right-radius: 1px 1px;
	border-bottom-right-radius: 1px 1px;
}

.tfbutton:hover {
	text-decoration: none;
	background: #007ead;
	background: -webkit-gradient(linear, left top, left bottom, from(#0095cc),
		to(#00678e));
	background: -moz-linear-gradient(top, #0095cc, #00678e);
}
/* Fixes submit button height problem in Firefox */
.tfbutton::-moz-focus-inner {
	border: 0;
}

.tfclear {
	clear: both;
}
</style>
<div style="position: relative;">
<div class="page-header">
	<h1>Customer Management</h1>
</div>

<div id="tfheader">
	<form id="tfnewsearch" method="post" action="e_customermanage.do">
		Research Customer User Name : 
		<input type="text" class="tftextinput" name="username" size="21" maxlength="120"> 
		<input type="submit" value="search" class="tfbutton">
	</form>
	<div class="tfclear"></div>
</div>

<table class="table table-hover">
	<c:if test="${requestScope.users!= null}">
		<thead>
			<th>C-ID</th>
			<th>UserName</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Cash</th>
			<th>Avaliable Cash</th>
			<th>ChangePassword</th>
			<th>ViewAccount</th>
			<th>TransactionHistory</th>
		</thead>
	<c:forEach items="${users}" var="user">
			<tr>
			
				<td>${users.customer_id}</td>
				<td>${users.username}</td>
				<td>${users.firstname}</td>
				<td>${users.lastname}</td>
				<td><fmt:formatNumber type="number" pattern="###,##0.00" value="${users.cash/100}"/></td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${users.tempcash/100}" /></td>
				<th>
					<form action="e_reset-pfc.do" method = "post">
					<input type="hidden" name="username" value="${users.username }" />
					<input type="submit" value="ChangePassword" class="tfbutton">
					</form>
				</th>
				
				<th>
					<form action="e_viewAllAccount.do" method = "post">
					<input type="hidden" name="username" value="${users.username}" />
					<input type="submit" value="ViewAccount" class="tfbutton">
					</form>
				</th>
				
				<th>
					<form action="e_viewTransactionHistory.do" method = "post">
					<input type="hidden" name="username" value="${ users.username }" />
					<input type="submit" value="TransactionHistory" class="tfbutton">
					</form>
				</th>
			
			</tr>
		</c:forEach>
	</c:if>
</table>
</div>
<jsp:include page="template-bottom.jsp" />
