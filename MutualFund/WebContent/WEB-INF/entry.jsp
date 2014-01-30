<html>
<head>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<link href="css/main.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">

<title>Mutual Fund</title>
</head>

<body>

	<table class="table">
		<tr>
			<!-- Banner row across the top -->
			<td width="130px" bgcolor="#2c3742">
			<td width="20px" bgcolor="#2c3742">&nbsp;</td>
			<td width="500px" bgcolor="#2c3742">

				<h1 style="color: white">
					<p class="text-center">Mutual Fund</p>
				</h1>

			</td>
		</tr>

		<!-- Spacer row -->

		<tr>
			<td valign="top" height="500" width="180px">
				<!-- Navigation bar is one table cell down the left side -->
				<div id="sidebar-wrapper">
					<ul class="sidebar-nav">

					</ul>
				</div>
			</td>

			<td>
				<!-- Padding (blank space) between navbar and content -->
			</td>
			<td valign="top"><jsp:include page="error-list.jsp" />
				<div class="text-center">
					<div class="page-header">
						<h1>Carnegie Financial Services</h1>
						<br> <br>
						<h4 class="form-signin-heading">Please choose your entrance</h4>
					</div>
					<div class="text-center">
						<ul class="list-inline">
							<li><a type="button" class="btn btn-primary btn-lg"
								href="c_login.do">Customer</a></li>
							<li></li>
							<li><a type="button" class="btn btn-primary btn-lg"
								href="admin_login.do">Employee</a></li>
						</ul>
					</div>
				</div> <jsp:include page="template-bottom.jsp" />