<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="template-top2.jsp" />
<jsp:include page="error-list.jsp" />

<style type="text/css">
td.right {
	text-align: right;
}
</style>

<div>
	<h1>Deposit Check</h1>
	<div id="left">
		<form action="e_depositCheck.do" method="POST">
			<table class="table">
				<tr>
					<td>To customer:</td>
					<td><input type="text" name="customer"
						placeholder="Enter name of customer" value="" /></td>
				</tr>
				<tr>
					<td>Amount:</td>
					<td><input type="text" name="amount" placeholder="0.00" /></td>
				</tr>
			</table>
			<input type="button" value="Cancel"
				class="btn btn-default btn-lg active" />
				
			<input type="submit"
				class="btn btn-primary btn-lg active" name="submitCheck"
				value="Submit" />
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />