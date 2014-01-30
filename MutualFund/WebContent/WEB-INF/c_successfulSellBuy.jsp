<!-- Yusi Zhang Jan.16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
<div style="position:relative;">
	<div class="page-header">
			<h1>Confimation Page</h1>
	</div>
	<h1>Successfully ${type} FundId: ${fundId} Shares: ${shares} Amount: ${amount}</h1>	
	<!-- Customer Information -->
	<table>
		<c:forEach var = "cusInfo" items = "${cusinfos}">
			<tr>
				<td>
					<!-- <form action="#remove.do" method="post">
						
					</form> -->
					${cusInfo.fundId}
				</td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.000" value="${cusInfo.shares/1000}" /></td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${cusInfo.amount/100}" /></td>
				<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${cusInfo.price/100}" /></td>
				<td>${cusInfo.status}</td>
			</tr>
		</c:forEach>
	</table>

</div>




<jsp:include page="template-bottom.jsp" />