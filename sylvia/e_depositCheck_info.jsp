<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title></title>
</head>
<body>

	<h2>Deposit Check</h2>
	<h3>Payment Information</h3>
	<div>
		<form action="#" method="POST">
			<table>
				<tr>
					<td style="font-size: large">To customer:</td>
					<td><input type="text" name="customer"
						placehodler="Enter name of customer" value="" /></td>
				</tr>
				<tr>
					<td style="font-size: large">Amount:</td>
					<td><input type="text" name="amount"
						placehodler="0.00" /></td>
				</tr>
				<tr>
					<td style="font-size: large">Personal Message:</td>
					<td><input type="text" name="message" /></td>
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