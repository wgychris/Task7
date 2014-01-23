<jsp:include page="template-top2.jsp" />
<body>
<p style="font-size:medium">
	
		<%  Object userName= request.getAttribute("userName");%>
	
	<h2>
		Reset Password For
		<%=userName %></h2>
</p>

<jsp:include page="error-list.jsp" />

<p>
	<form method="POST" action="e_reset-pfc.do">
		<table>
			<tr>
				<td> New Password: </td>
				<td><input type="password" name="newPassword" value=""/></td>
			</tr>
			<tr>
				<td> Confirm New Password: </td>
				<td><input type="password" name="confirmPassword" value=""/></td>
			</tr>
			
		</table>
		<button type="button" class="btn btn-default btn-lg active">Cancel</button>
		<button type="submit" class="btn btn-primary btn-lg active">Submit</button>
	</form>
</p>
</body>

<jsp:include page="template-bottom.jsp" />
