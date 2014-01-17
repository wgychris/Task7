<!-- Yusi Zhang Jan 16 Version 1.0 -->
<jsp:include page="template-top.jsp" />

<style type="text/css">
#tfheader {
	background-color: #c3dfef;
}
#tfheader2 {
	background-color: #F5F5DC;
}

#tfnewsearch {
	float: right;
	padding: 20px;
}

.tftextinput {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	border: 1px solid #0076a3;
	border-right: 0px;
	border-top-left-radius: 5px 5px;
	border-bottom-left-radius: 5px 5px;
}

.tfbutton {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	color: #ffffff;
	border: solid 1px #0076a3;
	border-right: 0px;
	background: #0095cd;
	background: -webkit-gradient(linear, left top, left bottom, from(#00adee),
		to(#0078a5));
	background: -moz-linear-gradient(top, #00adee, #0078a5);
	border-top-right-radius: 1px 1px;
	border-bottom-right-radius: 1px 1px;
}

.tfbutton:hover {
	text-decoration: none;
	background: #007ead;
	background: -webkit-gradient(linear, left top, left bottom, from(#0095cc),
		to(#00678e));
	background: -moz-linear-gradient(top, #0095cc, #00678e);
}
/* Fixes submit button height problem in Firefox */
.tfbutton::-moz-focus-inner {
	border: 0;
}

.tfclear {
	clear: both;
}
</style>
<div id="tfheader">
	<form id="tfnewsearch" method="get" action="http://www.google.com">
		<input type="text" class="tftextinput" name="q" size="21"
			maxlength="120"><input type="submit" value="search"
			class="tfbutton">
	</form>
	<div class="tfclear"></div>
</div>
<div id="tfheader2">
<table cellpadding="30">
		<tr>
			<td>Date              </td>
			<td>Closing Price    </td>
			<td>Action   </td>
		</tr>
	</table>
</div>


<jsp:include page="template-bottom.jsp" />