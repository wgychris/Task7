<jsp:include page="template-top2.jsp" />
<jsp:include page="error-list.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-header">
  <h1>View ${searcheduser.username}'s Transaction History:</h1>
</div>

<jsp:include page="error-list.jsp" />

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
 	<c:forEach var = "transaction" items = "${temptransactions}">
		<tr>
			<%-- <td><c:out value="${transaction.transaction_id}" /></td> --%>
			<td>${transaction.transaction_id}</td>
 			 <td>${transaction.name}</td>
			<td>${transaction.symbol}</td> 
			<td>${transaction.execute_date}</td>
			<td>${transaction.shares}</td>
			<td>${transaction.transaction_type}</td>
			<td><fmt:formatNumber type="number" 
            pattern="###.##" value="${transaction.amount}" /></td>
			
		</tr>
	</c:forEach> 
	
</table>
<jsp:include page="template-bottom.jsp" />
