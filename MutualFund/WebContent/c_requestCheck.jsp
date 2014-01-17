<!-- Daisy Wang Jan.16 Version 1.0 -->
<jsp:include page="template-top.jsp" />



<div>
	<div id="left">
		<form method="post" action="requestCheck.do">
			<table>
				<tr>
					<td>Amount: $</td>
					<td><input type="text" name="checkAmt"></td>
				</tr>
				<tr>
					<td>Memo: </td>
					<td><input type="text" name="amountId"></td>
				</tr>
				<tr>
					<td>One time or repeating?: </td>
					<td><input type="checkbox" name="option1" value="oneTime"> One-time</td>
				</tr>
				<tr>
					<td> </td>
					<td><input type="checkbox" name="option1" value="repeating"> Repeating</td>
				</tr>
				<tr>
					<td>Transfer Date: </td>
					<td><input type="text" name="transferDate" value = "${form.transferDate}"></td>
				</tr>
				
			</table>
			<input type="submit" name="submitCheck" value="Next"> <br />
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
