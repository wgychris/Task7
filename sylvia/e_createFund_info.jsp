<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>

	<h2>Deposit Check</h2>
	<h3>Fund Information</h3>
	<div>
		<form action="#" method="POST">
			<table>
				<tr>
					<td style="font-size: large">Fund name:</td>
					<td><input type="text" name="fund"
						placehodler="Enter name of fund" value="" /></td>
				</tr>
				<tr>
					<td style="font-size: large">Ticker:</td>
					<td><input type="text" name="ticker"
						placehodler="1-5 character identifier" /></td>
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