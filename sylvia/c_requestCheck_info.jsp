<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>

	<h2>Request Check</h2>
	<h3>Check Information</h3>
	<div>
		<form action="#" method="POST">
			<table>
				<tr>
					<td style="font-size: large">Amount:</td>
					<td><input type="text" name="amount"
						placeholder="no greater than ${balance}" value="" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button"
						value="cancel" /><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>