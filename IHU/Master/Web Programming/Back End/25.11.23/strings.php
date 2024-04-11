<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php
  $a = 5;
  $str = "ss    Free text    sssss";
  $str_2 = 'Ελληνικό κείμενο';

  echo $str;
  echo "<br>";
  echo $str_2;
  echo "<br>";
  echo "Number of bytes=" . strlen(trim($str));
  echo "<br>";
  echo "Number of characters=" . mb_strlen($str, "utf-8");
  echo "<br>";

  echo "Number of bytes=" . strlen($str_2);
  echo "<br>";
  echo "Number of caharacters=" . mb_strlen($str_2, "utf-8");

  echo "<br>";
  echo "Trimmed string: " . rtrim($str, "s");

  echo "<br>";
  echo str_replace("e", "a", $str);

  $new_str = trim(trim($str, "s"));
  echo "<br>";
  echo $new_str;

  echo "<br>";
  echo substr($new_str, 3, 30);

  echo "<br>";
  $last_name = "<b><i>html</i></b>";
  echo strip_tags($last_name);

  $b = "<br>" . "Data" . $a . "Structure";
  echo $b;
  ?>
</body>

</html>