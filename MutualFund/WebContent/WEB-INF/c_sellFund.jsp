<!-- Yusi Zhang Jan 16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />
<div style="position: relative;">
	<div class="page-header">
		<h1>Sell Fund</h1>
	</div>
	<div style="position: relative; float: left">
		<form method="post" action="c_sellFund.do">
			<label>Fund Ticker</label> <br /> <input type="text"
				class="form-control" name="fundTicker"><br /> <label>Shares</label>
			<br /> <input type="text" class="form-control" name="share"><br />
			<input type="submit" name="sell" value="Sell"
				class="btn btn-lg btn-primary btn-block"><br />
		</form>
	</div>
	<div id="right"
		style="position: relative; float: left; margin-left: 100px;">
		<label id="fundname">Fund Name : </label><br /> <label>${position.name }</label><br /> 
		<label id="ticker">Ticker : </label><br /> <label>${position.symbol }</label><br />
		<label id="price">Last price : </label><br /> <label>${price}</label><br /> 
		<label id="shares">Shares : </label><br /> <label></label>
		<fmt:formatNumber type="number" pattern="###.##"
			value="${position.shares/1000}" />
		<br /> <label id="availableshares">Available Shares : </label> <br /> <label></label>
		<fmt:formatNumber type="number" pattern="###.##"
			value="${position.tempshares/1000}" />
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