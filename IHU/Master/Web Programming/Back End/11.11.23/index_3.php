<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php

  $months = array();
  $months["m1"] = "January";
  $months["m2"] = "February";
  $months["m3"] = "March";
  foreach ($months as $k => $v) {
    echo "$k: $v<br>";
  }

  ?>
</body>

</html>