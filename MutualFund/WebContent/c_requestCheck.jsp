<!-- Daisy Wang Jan.16 Version 1.0 -->
<jsp:include page="template-top.jsp" />

<style type="text/css">
td.right {
	text-align: right;
}
</style>

<div>
	<h1>Request Check</h1>
	<div id="left">
		<form method="post" action="requestCheck.do">
			<table class="table">
				<tr>
					<td>Amount: $</td>
					<td><input type="text" name="checkAmt"></td>
				</tr>
				<tr>
					<td>Memo:</td>
					<td><input type="text" name="amountId"></td>
				</tr>
				<tr>
					<td>One time or repeating?:</td>
					<td><input type="checkbox" name="option1" value="oneTime">
						One-time</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="checkbox" name="option1" value="repeating">
						Repeating</td>
				</tr>
				<tr>
					<td>Transfer Date:</td>
					<td><input type="text" name="transferDate"
						value="${form.transferDate}"></td>
				</tr>
				<tr>
					<td></td>
					<td class="right"><input type="submit"
						class="btn btn-primary btn-lg active" name="submitCheck"
						value="Next"> <input type="button" value="Cancel"
						class="btn btn-primary btn-lg active" /></td>

				</tr>
			</table>

			<br />
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
