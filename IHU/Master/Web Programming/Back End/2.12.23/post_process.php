<?php
echo '<pre>';

echo "POST parameters:\n";


if (isset($_POST['first_name'])) {
	$first = strip_tags($_POST['first_name']);
} else {
	$first = "Unknown";
	// Perhaps do something here
}

if (isset($_POST['pass'])) {
	$pass = $_POST['pass'];
} else {
	$pass = "Unknown";
	// Perhaps do something here
}


if (isset($_POST['birthdate'])) {
	$birthdate = GetUserDate($_POST['birthdate']);
} else {
	echo "I need birthdate to continue!!! Aborting...";
	exit();
	// Perhaps do something here
}

echo "First: $first\n";
echo "Password: $pass";
echo "Birthdate: $birthdate";

$date = "02/12/2023/25";
print_r (explode("/", $date));

$str = "good day students";
print_r (explode(" ", $str));

function GetUserDate($UserDate) {
	$ret = trim($UserDate);
	$DateAr = explode("/", $ret);

	if (count($DateAr) == 3) {
		if (checkdate(intval($DateAr[1]), intval($DateAr[0]), intval($DateAr[2]))) {
			return $DateAr[2] . "-" . $DateAr[1] . "-" . $DateAr[0];
		} else {
			return 'inmvalid date';
		}
	} else {
		
		return "invalid date";
	}
}


?>