<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>
	<h2>Request Check</h2>
	<h3>Check Confirmation</h3>
	<div>
		<h5>Your check request has been submitted successfully</h5>
		<p>You have requested ${amount}.</p>
		<table>
			<tr>
				<td colspan="2" align="right"><input type="button"
					value="request another check" /> <input type="button"
					value="homepage" /></td>
			</tr>
		</table>
	</div>
</body>
</html>