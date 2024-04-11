<?php
// Start a session
session_start();
?>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Page B - PHP</title>
</head>

<body>
Hello, I am Page B and I will read my session...

<?php
var_dump($_SESSION);
?>
The value of my_var is: <?php echo $_SESSION["my_var"]; ?><br>
The value of my_int is: <?php echo $_SESSION["my_int"]; ?><br>
The value of my_float is: <?php echo $_SESSION["my_float"]; ?><br>
</body>
</html>