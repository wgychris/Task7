<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>

	<h2>Create Fund</h2>
	<h3>Verify Fund</h3>
	<div>
		<form action="#" method="POST">
			<input type="hidden" name="customer" value="${fund}" /> <input
				type="hidden" name="amount" value="${ticker}" />
			<table>
				<tr>
					<td style="font-size: large">Fund name:</td>
					<td style="font-size: large">${fund}</td>
				</tr>
				<tr>
					<td style="font-size: large">Ticker:</td>
					<td style="font-size: large">${ticker}"</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button"
						value="cancel" /><input type="submit" value="edit" /> <input
						type="submit" value="submit" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>