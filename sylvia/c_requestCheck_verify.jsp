<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>

	<h2>Request Check</h2>
	<h3>Verify Check</h3>
	<div>
		<form action="#" method="POST">
			<input type="hidden" name="amount" value="${amount}"/>
			<table>
				<tr>
					<td style="font-size: large">Amount:</td>
					<td style="font-size: large">${amount}</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input
						type="button" value="cancel" /><input type="submit" value="edit" /> <input type="submit"
						value="submit" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>