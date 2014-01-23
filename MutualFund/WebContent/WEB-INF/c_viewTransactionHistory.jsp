<jsp:include page="template-top.jsp" />
<%@ page import="databeans.TransactionBean"%>
<%@ page import="databeans.FundBean"%>
<%@ page import="databeans.CustomerBean"%>
<div class="page-header">
  <h1>View Transaction History:</h1>
</div>

<jsp:include page="error-list.jsp" />
<table>
	<%-- <c:forEach var = "transaction" items = "${transactions}">
		<tr>
			<td>${ transaction.transaction_id}</td>
			<td>${ transaction.fund_id}</td>
			<td>
				
			
			</td>
			<td>${ transaction.execute_date}</td>
			<td>${ transaction.shares}</td>
			<td>${ transaction.transaction_type}</td>
			<td>${ transaction.amount}</td>
			
		</tr>
	</c:forEach> --%>
	<%
		TransactionBean[] transactions = (TransactionBean[])request.getAttribute("transactions");
		FundBean [] funds = (FundBean[])request.getAttribute("funds");
		for(TransactionBean t : transactions){
	%>
		<td><%=t.getTransaction_id()%></td>
		<td><%%></td>
		<td><%=t.getTransaction_id()%></td>
		<td><%=t.getTransaction_id()%></td>
	
	<%
		}
	%>
</table>
<jsp:include page="template-bottom.jsp" />
