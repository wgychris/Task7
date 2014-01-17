<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<div>
	<h2>Deposit Check</h2>
	<h3>Verify Deposit</h3>
	<form action="#" method="POST">
		<input type="hidden" name="customer" value="${customer}" /> <input
			type="hidden" name="amount" value="${amount}" /> <input type="hidden"
			name="message" value="${message}" />
		<table>
			<tr>
				<td style="font-size: large">To customer:</td>
				<td style="font-size: large">${customer}</td>
			</tr>
			<tr>
				<td style="font-size: large">Amount:</td>
				<td style="font-size: large">${amount}"</td>
			</tr>
			<tr>
				<td style="font-size: large">Personal Message:</td>
				<td style="font-size: large">${message}</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button"
					value="cancel" /><input type="submit" value="edit" /> <input
					type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>

</div>

<jsp:include page="template-bottom.jsp" />