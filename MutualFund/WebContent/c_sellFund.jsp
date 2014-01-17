<!-- Yusi Zhang Jan 16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
<div style="position:relative;">
	<div class="page-header">
		<h1>Sell Fund</h1>
	</div>
	<div style="position:relative;float:left">
		<form method="post" action="#sell.do">
			<label>Fund Ticker</label> <br/>
			<input type="text" class="form-control" name="fundTicker" ><br/>
			<label>Shares</label> <br/>
			<input type="text" class="form-control" name="shares" ><br/>
			<input type="submit" name="sell" value="Sell" class="btn btn-lg btn-primary btn-block"><br/>	
		</form>
	</div>
	<table class="table table-hover">
		
			<tr>
				<td>#fund Ticker${cusInfo.fundTicker}</td>
				<td>#shares${cusInfo.shares}</td>
				<td>#amount${cusInfo.amount}</td>
				<td>#price${cusInfo.price}</td>
				<td>#status${cusInfo.status}</td>
			</tr>
			<tr>
				<td>123</td>
				<td>20</td>
				<td>pending</td>
				<td>800.00</td>
				<td>pending</td>
			</tr>
		
	</table>
	

	
</div>

<jsp:include page="template-bottom.jsp" />