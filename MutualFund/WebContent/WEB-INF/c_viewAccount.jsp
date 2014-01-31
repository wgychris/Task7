<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="template-top.jsp" />

<div class="page-header">
	<h1>View Customer Account:</h1>
</div>

<jsp:include page="error-list.jsp" />

<div>
	<h4>Customer Info</h4>
	<table  class="table table-striped">
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
			<td><fmt:formatNumber type="number" pattern="###,##0.00"
					value="${user.cash/100}" /></td>
		</tr>
	</table>
</div>
<hr>
<h4>Owned funds</h4>
<c:if test="${empty requestScope.userFundList}">
	<p>You haven't bought any fund</p>
</c:if>
<c:if test="${!empty requestScope.userFundList}">
	<table class="table table-hover">
		<c:if test="${requestScope.userFundList!= null}">
			<thead>
				<th>Fund name</th>
				<th>Fund ticker</th>
				<th class="text-right">Shares</th>
				<th class="text-right">Last price</th>
				<th class="text-right">Value</th>
				<th class="text-right">Operation</th>
			</thead>
			<c:forEach items="${requestScope.userFundList}" var="userFund">
				<tr>
					<td>${userFund.name}</td>
					<td>${userFund.symbol}</td>
					<td align="right"><fmt:formatNumber type="number" pattern="###,##0.000"
							value="${userFund.shares/1000}" /></td>
					<td align="right"><fmt:formatNumber type="number" pattern="###,##0.00"
							value="${userFund.price/100}" /></td>
					<td align="right"><fmt:formatNumber type="number" pattern="###,##0.00"
							value="${userFund.price/100 * userFund.shares/1000}" /></td>

					<td align="right"><a type="button" class="btn btn-default"
						href="c_buyFund.do">Buy</a> <a type="button"
						class="btn btn-default"
						href="c_sellFund.do?name=${userFund.name}&price=${userFund.price}">Sell</a></td>

					<!-- <td><a type="button" class="btn btn-default"
					href="c_buyFund.do">Buy</a> <a type="button"
					class="btn btn-default" href="c_sellFund.do">Sell</a></td> -->
				</tr>
			</c:forEach>
		</c:if>
	</table>
</c:if>


<jsp:include page="template-bottom.jsp" />
