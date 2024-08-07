<?php
$error = "";
if (isset($_GET['e'])) {
	$error = $_GET['e'];
}
?>

<html>
<head>
<title>User login</title>
</head>

<body>
	<form method="post" action="check_login.php">
		Username: <input type="text" name="ed_usr"><br>
		Password: <input type="password" name="ed_pwd"><br>
		<input type="submit" value="Login"><br><br>
<?php echo $error; ?>
	</form>
</body>
</html>


