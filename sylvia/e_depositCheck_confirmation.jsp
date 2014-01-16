<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>
	<h2>Deposit Check</h2>
	<h3>Deposit Confirmation</h3>
	<div>
		<h5>Your deposit has been submitted successfully</h5>
		<p>You have deposited ${amount} for ${customer}.</p>
		<p>The money will be available by the closing trading day.</p>
		<table>
			<tr>
				<td colspan="2" align="right"><input type="button"
					value="deposit another check" /> <input type="button" value="homepage" /></td>
			</tr>
		</table>
	</div>
</body>
</html>