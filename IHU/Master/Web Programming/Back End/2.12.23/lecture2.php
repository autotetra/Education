<?php 
// The execution of this script cannot last longer than 240 seconds.
ini_set("max_execution_time", 120);

// Thhis script cannot consume more than 512 MB of RAM.
ini_set("memory_limit", "512M");


$user_id = 50;
$login = 1;

$cookie_string = $login . ";" . $user_id;

// Cookie Name: MyFirstCookie
// Cookie Data: $cookie_string
// Expriation: 30 days from now (in seconds)
setcookie("MyFirstCookie", $cookie_string, time() + (86400 * 30));
setcookie("MySecondCookie", $user_id, time() + (86400 * 40));
setcookie("MyThirdCookie", $login, time() + (86400 * 50));

var_dump($_COOKIE);
?>
