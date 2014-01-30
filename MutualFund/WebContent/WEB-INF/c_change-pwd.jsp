<jsp:include page="template-top.jsp" />

<div class="page-header">
	<h1>Change your password</h1>
</div>
<script language="javascript" type="text/javascript">
	function cancelAction() {
		document.location.href("c_sellFund.do");
	}
</script>

<% 
String myPage = "c_viewAccount.do"; 
%>
<jsp:include page="error-list.jsp" />

<p>
<form method="POST" action="c_change-pwd.do">
	<table class="table table-hover">
		<tr>
			<td>New Password: *</td>
			<td><input type="password" name="newPassword" value="" /></td>
		</tr>
		<tr>
			<td>Confirm Password: *</td>
			<td><input type="password" name="confirmPassword" value="" /></td>
		</tr>
		<tr>
			<td>&nbsp;
			<td>
		</tr>
		<tr>
			<td>&nbsp;
			<td>
		</tr>
	</table>
	<input type="button" onclick="javascript:cancelAction();" class="btn btn-default btn-lg active" value="Cancel"></a>
	<input type="button" value="Add" onClick="javascript:window.location='<%= myPage %>';"> 
	<button type="submit" class="btn btn-primary btn-lg active">Submit</button>
</form>
</p>

<jsp:include page="template-bottom.jsp" />
