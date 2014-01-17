<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<div>

	<h2>Transition Day</h2>
	<h3>Verify Transition Day</h3>
	<form action="#" method="POST">
		<input type="hidden" name="data" value="${date}" />
		<table>
			<tr>
				<td style="font-size: large">Trading Day:</td>
				<td style="font-size: large">${date}:</td>
			</tr>
		</table>

		<table>
			<c:if test="${requestScope.fund != null}">
				<tr>
					<td style="font-size: large">Fund</td>
					<td style="font-size: large">Ticker</td>
					<td style="font-size: large">New Closing Price</td>
				</tr>
				<c:forEach items="${requestScope.fund}" var="fund">
					<tr>
						<td style="font-size: large">${fund}</td>
						<td style="font-size: large">${ticker}</td>
						<td style="font-size: large">${price}</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<tr>
			<td colspan="2" align="right"><input type="button"
				value="cancel" /><input type="submit" value="edit" /> <input
				type="submit" value="submit" /></td>
		</tr>
		</table>
	</form>

</div>

<jsp:include page="template-bottom.jsp" />