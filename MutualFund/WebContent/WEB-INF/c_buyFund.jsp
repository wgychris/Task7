<!-- Yusi Zhang Jan.16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<div style="position: relative;">
	<div class="page-header">
		<h1>Buy Fund</h1>
	</div>
	<div id="left" style="position: relative; float: left;">
		<form method="post" action="c_buyFund.do">
			<label>Fund Ticker </label> <input type="text" name="fundTicker"
				class="form-control"> <br /> <label>Dollar Amount $
			</label> <input type="text" name="amount" class="form-control"> <br />
			<input type="submit" name="buy" value="Buy Fund"
				class="btn btn-lg btn-primary btn-block"> <br />
		</form>
	</div>

	<div id="right"
		style="position: relative; float: left; margin-left: 100px;">
		<label id="cashbalance">Cash Balance : </label><br /> <label>$
		</label> XXXX${customr.cash}
	</div>

</div>

<jsp:include page="template-bottom.jsp" />
