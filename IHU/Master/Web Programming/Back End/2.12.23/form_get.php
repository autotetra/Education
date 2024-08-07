<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Form Example</title>
</head>

<body>
	<form method="get" action="get_process.php">
		<label for="member_name_id">Full Name:</label>
		<input type="text" name="member_name" id="member_name_id">
		<br><br>

		<label for="member_email_id">e-mail adr.:</label>
		<input type="text" name="member_email" id="member_email_id">
		<br><br>

		<label for="product_id">Footware:</label>
		<select name="product" id="product_id">
			<option value="1">Shoes</option>
			<option value="2">Boots</option>
		</select>
		<br><br>

		<input type="hidden" name="member_hidden" value="secret data">

		<input type="submit" value="Submit">
	</form>
</body>
</html>


