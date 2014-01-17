<jsp:include page="template-top.jsp" />

<div class="page-header">
  <h1>View Customer Account:</h1>
</div>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="view.do">
		<table>
		    <tr>
				<td> Customer ID: </td>
				<td><input type="text" name="customerId" value=""/></td>
			</tr>
			<tr>
				<td> Customer Name: </td>
				<td><input type="text" name="userName" value=""/></td>
			</tr>
			<tr>
				<td> First Name: </td>
				<td><input type="text" name="firstName" value=""/></td>
			</tr>
			<tr>
				<td> Last Name: </td>
				<td><input type="text" name="lastName" value=""/></td>
			</tr>
			<tr>
				<td> Address: </td>
				<td><input type="text" name="address" value=""/></td>
			</tr>
			<tr>
				<td> City: </td>
				<td><input type="text" name="city" value=""/></td>
			</tr>
			<tr>
				<td> State: </td>
				<td><input type="text" name="state" value=""/></td>
			</tr>
			<tr>
				<td> Zip: </td>
				<td><input type="text" name="zipCode" value=""/></td>
			</tr>
			<tr>
				<td> Last Trading Day: </td>
				<td><input type="text" name="lastTradingDay" value=""/></td>
			</tr>
			<tr>
				<td> Cash Balance: </td>
				<td><input type="text" name="cashBalance" value=""/></td>
			</tr>
			<tr><td>&nbsp;  <td></tr>
			<tr><td>&nbsp;  <td></tr>
		</table>	
		<table border="1">
		    <tr>
				<th> Fund ID </th>
				<th> Shares</th>
				<th> Execute Date</th>
				<th> Transaction Type</th>
				<th> Amount</th>
				<th> Position</th>
			</tr>
			<tr><td>&nbsp;  <td><td>&nbsp;  <td><td>&nbsp;  <td></tr>
			<tr><td>&nbsp;  <td><td>&nbsp;  <td><td>&nbsp;  <td></tr>
		</table>
		<table>
		    <tr><td>&nbsp;  <td></tr>
			<tr><td>&nbsp;  <td></tr>

		</table>
		<button type="button" class="btn btn-default btn-lg active">Back</button>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
