<jsp:include page="template-top2.jsp" />
<jsp:include page="error-list.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page-header">
	<h1>View ${searcheduser.username}'s Transaction History:</h1>
</div>

<c:if test="${ empty temptransactions}">
	<p>No transaction yet</p>
</c:if>

<c:if test="${! empty temptransactions}">
	<table class="table table-hover">
		<tr>
			<td>Transaction_ID</td>
			<td>Fund Name</td>
			<td>Fund Ticker</td>
			<td>Execute Date</td>
			<td>Shares</td>
			<td>Type</td>
			<td>Amount</td>

		</tr>

		<c:forEach var="transaction" items="${temptransactions}">
			<tr>
				<%-- <td><c:out value="${transaction.transaction_id}" /></td> --%>
				<td>${transaction.transaction_id}</td>
				<td>${transaction.name}</td>
				<td>${transaction.symbol}</td>
				<td>${transaction.execute_date}</td>
				<td><fmt:formatNumber type="number" pattern="###,##0.000"
						value="${transaction.shares/1000}" /></td>
				<td>${transaction.transaction_type}</td>
				<td><fmt:formatNumber type="number" pattern="###,##0.00"
						value="${transaction.amount/100}" /></td>

			</tr>
		</c:forEach>
		</c:if>

	</table>
	<jsp:include page="template-bottom.jsp" />