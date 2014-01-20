<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top2.jsp" />
<jsp:include page="error-list.jsp" />

<style type="text/css">
td.right {
	text-align: right;
}
</style>

<div>
	<h1>Transition Day</h1>
	<div>
		<form action="#" method="POST">
			<table class="table">
				<tr>
					<td>Trading Day:</td>
					<td><input type="text" name="fund"
						placeholder="later than the previously ended trading day" value="" /></td>
				</tr>
			</table>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Fund</th>
						<th>Ticker</th>
						<th>Current Price</th>
						<th>New Closing Price</th>
					</tr>
				</thead>
				<tboday>
				<tr>
					<td>Fund</td>
					<td>Ticker</td>
					<td>Current Price</td>
					<td>New Closing Price</td>
				</tr>
				<c:if test="${requestScope.fund != null}">
					<tr>
						<td>Fund</td>
						<td>Ticker</td>
						<td>Current Price</td>
						<td>New Closing Price</td>
					</tr>
					<c:forEach items="${requestScope.fund}" var="fund">
						<tr>
							<td>${fund}</td>
							<td>${ticker}</td>
							<td>${fund.price}</td>
							<td><input type="text" placeholder="0.000" value="" /></td>
						</tr>
					</c:forEach>
				</c:if> </tboday>

			</table>

			<input type="button" value="Cancel"
				class="btn btn-default btn-lg active" /><input type="submit"
				class="btn btn-primary btn-lg active" name="submitCheck"
				value="Next">
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />