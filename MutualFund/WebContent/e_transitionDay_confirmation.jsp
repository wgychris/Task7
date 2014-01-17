<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<div>
	<h2>Transition Day</h2>
	<h3>Transition Day Confirmation</h3>
	<h5>Your have successfully transitioned the day ${date }.</h5>
	<table>
		<tr>
			<td colspan="2" align="right"><input type="button"
				value="homepage" /></td>
		</tr>
	</table>
</div>

<jsp:include page="template-bottom.jsp" />