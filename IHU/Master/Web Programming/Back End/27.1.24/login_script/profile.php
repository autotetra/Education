<?php
session_start();

$login = 0;

if (isset($_SESSION['user_name'])) {
    if ($_SESSION['user_name'] != "") {
        $login = 1;
    }
}

if ($login == 0) {
    echo "You do not have access";
    exit();
}
?>

<html>

<head>
	<title>Profile of user
		<?php echo $_SESSION['user_name']; ?>
	</title>
</head>

<body>
	Welcome
	<?php echo $_SESSION['user_name']; ?>!!
	<br><br>
	<a href="logout.php">Logout</a>
</body>

</html>