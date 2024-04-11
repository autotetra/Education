<?php
var_dump($_GET);

// Store the value of the form field
// "member_name" in a variable "name".
if (isset($_GET['member_name'])) {
	$name = $_GET['member_name'];
} else {
	$name = "";
}

// Store the value of the form field
// "member_email" in a variable "email"
if (isset($_GET['member_email'])) {
	$email = $_GET['member_email'];
} else {
	$email = "";
}

// Store the value of the form field
// "product" in a variable "product"
if (isset($_GET['product'])) {
	$product = $_GET['product'];
} else {
	$product = "";
}
// Store the value of the form field
// "member_hidden" in a variable "hidden_data"
if (isset($_GET['member_hidden'])) {
	$hidden_data = $_GET['member_hidden'];
} else {
	$hidden_data = "";
}


echo "$name<br>$email<br>$product<br>$hidden_data";


?>