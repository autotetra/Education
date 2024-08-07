<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>

<body>
  <?php

  class car
  {
    private $id;
    private $year;
    protected $brand;
    protected $engine;
    protected $doors;
    private $color;
    private $price;

    // Constructor
    public function __construct($year, $brand, $id, $color, $engine, $doors, $price)
    {
      $this->id = $id;
      $this->year = $year;
      $this->brand = $brand;
      $this->engine = $engine;
      $this->doors = $doors;
      $this->color = $color;
      $this->price = $price;
    }

    // Getters: Functions for accessing the values of the members
    public function get_id()
    {
      return $this->id;
    }
    public function get_brand()
    {
      return $this->brand;
    }
    public function get_engine()
    {
      return $this->engine;
    }
    public function get_doors()
    {
      return $this->doors;
    }

    // Setters: Functions for changing the values of the members
    public function set_id($v)
    {
      $this->id = $v;
    }
    public function set_brand($v)
    {
      $this->brand = $v;
    }
    public function set_engine($v)
    {
      $this->engine = $v;
    }
    public function set_doors($v)
    {
      $this->doors = $v;
    }
    public function set_color($v)
    {
      $this->color = $v;
    }
    public function set_price($v)
    {
      $this->price = $v;
    }
    public function set_year($v)
    {
      $this->year = $v;
    }
  };


  $opel_astra = new car(2020, "Opel", 1, "black", "v10", "4", "1.000$");

  echo '<Pre>';
  print_r($opel_astra);
  echo '</pre>';

  $mercedes = new car(2013, "Mercedes", 2, "gray", "v11", "3", "2.000$");

  echo '<Pre>';
  print_r($mercedes);
  echo '</pre>';

  ?>
</body>

</html>