<!-- Yusi Zhang Jan.16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<div style="position:relative;">
	<div id="left" style="position:relative;float:left">
		<form method="post" action="#buy.do">
			<label>Fund Ticker </label> <input type="text" name="fundTicker"> <br/>
			<label>Dollar Amount $ </label> <input type="text" name="amountId"> <br/>
			<input type="submit" name="buy" value="Buy Fund"> <br/> 
		</form>
	</div>
	
	<div id="right" style="position:relative;float:right">
		<label id="cashbalance">Cash Balance :</label><br/>
		<label>$ </label> ${customr.cash}
	</div>
	
</div>

<jsp:include page="template-bottom.jsp" />
