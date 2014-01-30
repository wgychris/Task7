<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="template-top.jsp" />

<div class="page-header">
	<h1>View Customer Account:</h1>
</div>

<jsp:include page="error-list.jsp" />

<div>
	<dl class="dl-horizontal">
		<dt>Customer ID:</dt>
		<dd>${user.customer_id}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>User Name:</dt>
		<dd>${user.username}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>First Name:</dt>
		<dd>${user.firstname}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>Last Name:</dt>
		<dd>${user.lastname}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>Address:</dt>
		<dd>${user.addr1}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>City:</dt>
		<dd>${user.city}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>State:</dt>
		<dd>${user.state}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>Zip:</dt>
		<dd>${user.zip}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>Last Trading Day:</dt>
		<dd>${day}</dd>
	</dl>
	<dl class="dl-horizontal">
		<dt>Cash Balance:</dt>
		<dd><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${user.cash/100}" /></dd>
	</dl>
</div>

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
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.000" value="${userFund.shares/1000}" /></td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${userFund.price/100}" /></td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${userFund.price/100 * userFund.shares/1000}" /></td>
				
				<td>
				<a type="button" class="btn btn-default"
					href="c_buyFund.do">Buy</a>
				<a type="button" class="btn btn-default"
					href="c_sellFund.do?name=${userFund.name}&price=${userFund.price/100}">Sell</a></td>
				
				<!-- <td><a type="button" class="btn btn-default"
					href="c_buyFund.do">Buy</a> <a type="button"
					class="btn btn-default" href="c_sellFund.do">Sell</a></td> -->
			</tr>
		</c:forEach>
	</c:if>
</table>


<jsp:include page="template-bottom.jsp" />