<jsp:include page="template-top2.jsp" />

<div class="page-header">
  <h1>Create Customer Account:</h1>
</div>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="c_create_customer.do">
		<table>
			<tr>
				<td> userName: *</td>
				<td><input type="text" name="userName" value=""/></td>
			</tr>
			<tr>
				<td> Password: *</td>
				<td><input type="text" name="password" value=""/></td>
			</tr>
			<tr>
				<td> First Name: *</td>
				<td><input type="text" name="firstName" value=""/></td>
			</tr>
			<tr>
				<td> Last Name: *</td>
				<td><input type="text" name="lastName" value=""/></td>
			</tr>
			<tr>
				<td> Address Line 1: *</td>
				<td><input type="text" name="address1" value=""/></td>
			</tr>
			<tr>
				<td> Address Line 2: *</td>
				<td><input type="text" name="address2" value=""/></td>
			</tr>
			<tr>
				<td> City: *</td>
				<td><input type="text" name="city" value=""/></td>
			</tr>
			<tr>
				<td> State: *</td>
				<td><input type="text" name="state" value=""/></td>
			</tr>
			<tr>
				<td> Zip: *</td>
				<td><input type="text" name="zipCode" value=""/></td>
			</tr>
			<tr>
				<td> Cash: </td>
				<td><input type="text" name="cash" value=""/></td>
			</tr>
			<tr><td>&nbsp;  <td></tr>
			<tr><td>&nbsp;  <td></tr>
		</table>	
		
			   <button type="button" class="btn btn-default btn-lg active">Reset</button>
				<button type="submit" class="btn btn-primary btn-lg active">Submit</button>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
