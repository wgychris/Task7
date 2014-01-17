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
		<form action="#" method="POST">
			<table class="table">
				<tr>
					<td>Fund name:</td>
					<td><input type="text" name="fund"
						placehodler="Enter name of fund" value="" /></td>
				</tr>
				<tr>
					<td>Ticker:</td>
					<td><input type="text" name="ticker"
						placehodler="1-5 character identifier" /></td>
				</tr>
			</table>
			<input type="button" value="Cancel"
				class="btn btn-default btn-lg active" /><input type="submit"
				class="btn btn-primary btn-lg active" name="submitCheck"
				value="Next">
		</form>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />