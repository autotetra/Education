<?php
$temp = $_FILES["file_selector"]["tmp_name"];
$perm = "uploads/" . $_FILES["file_selector"]["name"];
if (move_uploaded_file($temp, $perm)) {
	echo "The file was uploaded and moved succssfully.";
} else {
	echo "There was a problem moving the file";
}
?>
