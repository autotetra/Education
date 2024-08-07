<?php

// Start a session
session_start();

// Set two session variables: my_var and my_int
$_SESSION['my_var'] = "A session variable";
$_SESSION['my_int'] = 5;

echo "Session was created. Its contents are:";
var_dump($_SESSION);
?>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Page A - PHP</title>
</head>

<body>
Now go to <a href="session_page_b.php">Page B</a>
to check if the session contents are preserved.

</body>
</html>