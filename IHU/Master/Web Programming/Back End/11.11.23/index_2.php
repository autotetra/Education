<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php
  function add($x, $y = 1)
  {
    return $x + $y;
  }
  function doubleMe($a)
  {
    $a *= 2;
    echo "inside function, a=$a";
  }

  // $a = 10;
  // doubleMe($a);
  // echo "<br>";
  // echo "outside function, a=$a";

  $var_1 = 10;

  function fun()
  {
    $var_2 = 20;
    echo "In fun: var_1=$var_1<br>";
    echo "in fun: var_2=$var_2<br>";
  }

  fun();

  echo ("Out fun: var_1=$var_1<br>");
  echo ("Out fun: var_2=$var_2<br>");


  ?>
</body>

</html>