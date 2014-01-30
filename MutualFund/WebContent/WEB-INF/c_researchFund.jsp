<!-- Daisy Wang Jan 16 Version 1.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<%@ page import="databeans.FundPriceHistoryBean"%>
<%
	if (request.getAttribute("fundInfo") != null) {
%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    
    
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	var data = new google.visualization.DataTable();
    	var jsArr = new Array();  
  	    var jsArr2 = new Array();
  	    
  	    <%
  	    FundPriceHistoryBean[] fps= (FundPriceHistoryBean[])request.getAttribute("fundInfo");
  	    for (int i=0; i < fps.length; i++) {  
  	    %>  
  	    jsArr[<%= i %>] = '<%=fps[i].getDate() %>'; 
  	    <%}%>
  	    <%  
  	    for (int i=0; i < fps.length; i++) {  
  	    %>  
  	    jsArr2[<%= i %>] = '<%=fps[i].getPrice() %>'; 
  	    <%}%>
    	  data.addColumn('string','Date');
    	  data.addColumn('number', 'Price');
    	  data.addRows(jsArr.length);
    	  //alert(jsArr.length);
    	  <%  
    	    for (int i=0; i < fps.length; i++) {
    	    	if(i==0 || i==fps.length-1){
    	    %>
    	    data.setValue(<%= i %>,0, jsArr[<%= i %>]);
    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
    	    <%}else{%>
    	    data.setValue(<%= i %>,0, "");
    	    data.setValue(<%= i %>,1, parseInt(jsArr2[<%= i %>]));
    	    <%}
    	    }
    	  %>
    	  
        var options = {
          title: 'Price history'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    <%
	}
%>
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
	<form id="tfnewsearch" method="post" action="c_researchFund.do">
		Research Fund : <input type="text" class="tftextinput"
			name="fundTicker" size="21" maxlength="120"><input
			type="submit" value="search" class="tfbutton">
	</form>
	<div class="tfclear"></div>
</div>
<div id="tfheader2">
<div id="chart_div" style="padding-left: 100px; width: 900px; height: 450px;"></div>
	<!--  <table class="table table-hover">
	<%
	if (request.getAttribute("fundInfo") != null) {
%>
<tr>
			<td>Date</td>
			<td>Closing Price</td>
		</tr>
		<%
    for (FundPriceHistoryBean f : (FundPriceHistoryBean[])request.getAttribute("fundInfo")) {
%>
		<tr>
			<td><%=f.getDate()%></td>
			<td><%=f.getPrice()%></td>
		</tr>
		<%
		}
%>

	</table>-->
	<%
    } 
%>

</div>


<jsp:include page="template-bottom.jsp" />