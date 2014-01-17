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
				<td>${cusInfo.shares}</td>
				<td>${cusInfo.amount}</td>
				<td>${cusInfo.price}</td>
				<td>${cusInfo.status}</td>
			</tr>
		</c:forEach>
	</table>

</div>




<jsp:include page="template-bottom.jsp" />