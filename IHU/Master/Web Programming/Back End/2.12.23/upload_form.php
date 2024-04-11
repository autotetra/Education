<html>
<head>
<meta charset="utf-8">
<title>File Uploader</title>
</head>

<body>
This form allows the uploading of files<br><br>
<form method="post" action="upload.php" enctype="multipart/form-data">
<label for="file_selector_id">Select a file:</label>
<input type="file" name="file_selector" id="file_selector_id">
<br><br>
<input type="submit" value="Upload">
</form>
</body>
</html>