<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<div>
	<h2>Transition Day</h2>
	<h3>Date and Price Information</h3>
	<form action="#" method="POST">
		<table>
			<tr>
				<td style="font-size: large">Trading Day:</td>
				<td><input type="text" name="fund"
					placehodler="later than the previously ended trading day" value="" /></td>
			</tr>
		</table>

		<table>
			<c:if test="${requestScope.fund != null}">
				<tr>
					<td style="font-size: large">Fund</td>
					<td style="font-size: large">Ticker</td>
					<td style="font-size: large">Current Price</td>
					<td style="font-size: large">New Closing Price</td>
				</tr>
				<c:forEach items="${requestScope.fund}" var="fund">
					<tr>
						<td style="font-size: large">${fund}</td>
						<td style="font-size: large">${ticker}</td>
						<td style="font-size: large">${fund.price}</td>
						<td><input type="text" style="font-size: large"
							placehodler="0.000" value="" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<table>
			<tr>
				<td colspan="2" align="right"><input type="button"
					value="cancel" /><input type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</div>

<jsp:include page="template-bottom.jsp" />