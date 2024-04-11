<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php
  require("OOP_cars.php");

  print_r($bmw);
  echo '<Pre>';
  $bmw = new car("1", "2", "3", "4", "5", "6", "7");
  echo '</pre>';
  echo date("D, d-M-Y");
  ?>
</body>

</html>