<!-- Daisy Wang Jan.16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="template-top.jsp" />

<div style="position: relative;">
	<style type="text/css">
td.right {
	text-align: right;
}
</style>

	<div class="page-header">
		<h1>Request Check</h1>
	</div>
	<div id="left" style="position: relative; float: left;">
		<form method="post" action="c_requestCheck.do">
			<table class="table">
				<tr>
					<td>Amount: $</td>
					<td><input type="text" name="checkAmt"></td>
				</tr>
				<tr>
					<td></td>
					<td class="right"><input type="submit"
						class="btn btn-primary btn-lg active" name="submitCheck"
						value="Next"></td>
				</tr>
			</table>

			<br />
		</form>
	</div>

	<div id="right"
		style="position: relative; float: left; margin-left: 100px;">
		<label id="cashbalance">Cash Balance : </label><br /> <label>$</label>
		<fmt:formatNumber type="number" pattern="###,##0.00"
			value="${sessionScope.customer.cash/100}" />
		<br /> <label id="availablecash">Available Cash : </label> <br /> <label>$</label>
		<fmt:formatNumber type="number" pattern="###,##0.00"
			value="${sessionScope.customer.tempcash/100}" />
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
