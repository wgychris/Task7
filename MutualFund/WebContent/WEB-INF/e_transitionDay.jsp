<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="template-top2.jsp" />
<jsp:include page="error-list.jsp" />

<div>
	<h1>Transition Day</h1>
	<div>
		<form action="e_transitionDay.do" method="POST">
			<table class="table">
				<tr>
					<td>Trading Day:</td>
					<td><input type="text" name="transitionDay"
						placeholder="later than the previously ended trading day" value="" /></td>
				</tr>
			</table>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>FundName</th>
						<th>Ticker</th>
						<th>Last Closing Price</th>
						<th>New Closing Price</th>
					</tr>
				</thead>
				<tboday>
				
				<c:if test="${requestScope.funds!= null}">
					<c:forEach items="${requestScope.funds}" var="fund">
						<tr>
							<td>${fund.name}</td>
							<td>${fund.symbol}</td>
							<c:if test="${fund.price!=0}">
							<td><fmt:formatNumber type="number" 
            pattern="###,##0.00" value="${fund.price/100}" /></td>
							</c:if>
							<c:if test="${fund.price==0}">
							<td>No price yet</td>
							</c:if>
							<td><input type="text" placeholder="0.00" name="price" value="" />
							<input type="hidden" name="fund_id" value="${fund.fund_id }"></td>
						</tr>
					</c:forEach>
				</c:if> </tboday>

			</table>

			<input type="submit"
				class="btn btn-primary btn-lg active" name="submitCheck"
				value="Next">
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
