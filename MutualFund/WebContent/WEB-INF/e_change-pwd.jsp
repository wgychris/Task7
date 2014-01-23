<jsp:include page="template-top2.jsp" />

<div class="page-header">
  <h1>Change your password </h1>
</div>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="e_change-pwd.do">
		<table class="table table-hover">
			<tr>
				<td> Old Password: *</td>
				<td><input type="password" name="oldPassword" value=""/></td>
			</tr>
			<tr>
				<td> New Password: *</td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm Password: *</td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			<tr><td>&nbsp;  <td></tr>
			<tr><td>&nbsp;  <td></tr>
		</table>
		<button type="button" class="btn btn-default btn-lg active">Cancel</button>
		<button type="submit" class="btn btn-primary btn-lg active">Submit</button>
	</form>
</p>

<jsp:include page="template-bottom.jsp" />
