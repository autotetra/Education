<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php
  echo "Result 1: " . add(9, 2) . "<br>";
  echo "Result 2: " . substract(add(5, 2), add(7, 3)) . "<br>";
  echo "absolute: " . pabs(add(5, 2), add(7, 3)) . "<br>";
  echo "Eucledean Distance (5,6), (8,2): " . distance(5, 6, 8, 2) . "<br>";

  function add($x, $y)
  {
    return $x + $y;
  }
  function substract($x, $y)
  {
    return $x - $y;
  }

  function pabs($x, $y)
  {
    if ($x >= $y) {
      return $x - $y;
    } else {
      return $y - $x;
    }
  }
  function distance($x1, $y1, $x2, $y2)
  {
    return sqrt(pow($y2 - $y1, 2) + pow($x2 - $x1, 2));
  }

  ?>
</body>

</html>