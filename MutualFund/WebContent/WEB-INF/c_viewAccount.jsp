<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<div class="page-header">
	<h1>View Customer Account:</h1>
</div>

<jsp:include page="error-list.jsp" />

<table class="table table-hover">
	<tr>
		<td>Customer ID:</td>
		<td>${user.customer_id}</td>
	</tr>
	<tr>
		<td>User Name:</td>
		<td>${user.username}</td>
	</tr>
	<tr>
		<td>First Name:</td>
		<td>${user.firstname}</td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td>${user.lastname}</td>
	</tr>
	<tr>
		<td>Address:</td>
		<td>${user.addr1}</td>
	</tr>
	<tr>
		<td>City:</td>
		<td>${user.city}</td>
	</tr>
	<tr>
		<td>State:</td>
		<td>${user.state}</td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td>${user.zip}</td>
	</tr>
	<tr>
		<td>Last Trading Day:</td>
		<td>${day}</td>
	</tr>
	<tr>
		<td>Cash Balance:</td>
		<td>${user.cash }</td>
	</tr>
</table>

<table class="table table-hover">
	<c:if test="${requestScope.userFundList!= null}">
		<thead>
			<th>Fund ID</th>
			<th>Shares</th>
			<th>Last Execute Date</th>
			<th>Last Price</th>
			<th>Value</th>
			<th>Operation</th>
		</thead>>
					<c:forEach items="${requestScope.userFundList}" var="userFund">
			<tr>
				<td>${userFund.fund_id}</td>
				<td>${userFund.shares}</td>
				<td>${userFund.price_date}</td>
				<td>${userFund.price}</td>
				<td>${userFund.price * userFund.shares}</td>
				<td>operation</td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<button type="button" class="btn btn-default btn-lg active">Back</button>

<jsp:include page="template-bottom.jsp" />
