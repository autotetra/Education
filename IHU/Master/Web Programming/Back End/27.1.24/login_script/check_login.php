<?php
require("pdo.php");
session_start();

if(isset($_POST['ed_usr'])) {
	$username = trim($_POST['ed_usr']);
} else {
	$username = "";
}

if(isset($_POST['ed_pwd'])) {
	$password = trim($_POST['ed_pwd']);
} else {
	$password = "";
}

if ($username == "" || $password == "") {
	header("location:login.php?e=Please enter username and password");
	exit();
}

$sel_stmt = $PDO->prepare("SELECT * FROM users WHERE user_username = ? AND user_password = ?");
$sel_stmt->execute( [$username, $password] );

$login = $sel_stmt->rowCount();

if ($login == 0) {
	header("location:login.php?e=Incorrect username and/or password");
	exit();
} else if ($login == 1) {
	$row = $sel_stmt->fetch();
	$_SESSION['user_name'] = $row['user_name'];

	header("location:profile.php");
	exit();
}
?>
