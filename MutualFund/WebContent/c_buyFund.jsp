<!-- Yusi Zhang Jan.16 Version 1.0 -->
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<div>
	<div id="left">
		<form method="post" action="buyfund.do">
			<label>Fund Ticker </label> <input type="text" name="fundTicker"> <br/>
			<label>Dollar Amount $ </label> <input type="text" name="amountId"> <br/>
			<input type="submit" name="buy" value="Buy Fund"> <br/> 
		</form>
	</div>
	
	<div id="right">
		<label id="cashbalance">Cash Balance :</label><br/>
		<label>$ </label> ${customr.cash}
	</div>
	
</div>

<jsp:include page="template-bottom.jsp" />
