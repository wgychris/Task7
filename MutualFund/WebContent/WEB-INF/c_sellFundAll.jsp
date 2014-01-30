<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="template-top.jsp" />

<div class="page-header">
	<h1>Sell Fund</h1>
</div>

<jsp:include page="error-list.jsp" />

<table class="table table-hover">
	<c:if test="${requestScope.userFundList!= null}">
		<thead>
			<th>Fund name</th>
			<th>Fund ticker</th>
			<th>Shares</th>
			<th>Last price</th>
			<th>Value</th>
			<th>Operation</th>
		</thead>
		<c:forEach items="${requestScope.userFundList}" var="userFund">
			<tr>
				<td>${userFund.name}</td>
				<td>${userFund.symbol}</td>
				<td><fmt:formatNumber type="number" pattern="###,##0.000"
						value="${userFund.shares/1000}" /></td>
				<td><fmt:formatNumber type="number" pattern="###,##0.00"
						value="${userFund.price/100}" /></td>
				<td><fmt:formatNumber type="number" pattern="###,##0.00"
						value="${userFund.price * userFund.shares/100000}" /></td>
				<td><a type="button" class="btn btn-default"
					href="c_sellFund.do?name=${userFund.name}&price=${userFund.price}">Sell</a></td>
			</tr>
		</c:forEach>
	</c:if>
</table>


<jsp:include page="template-bottom.jsp" />
