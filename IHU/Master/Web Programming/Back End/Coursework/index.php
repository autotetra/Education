<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "OrderAppDB";

$conn = mysqli_connect($servername, $username, $password, $dbname);


if(!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$query = "SELECT * FROM categories WHERE category_active = 1";
$result = mysqli_query($conn, $query);

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>QuickMart | Homepage</title>
</head>

<body>
    <h2 id='headers'>Categories</h2>
    <div id="categories">
        <?php
        while($row = mysqli_fetch_assoc($result)) {
            $categoryId = $row['category_id'];
            $categoryName = $row['category_name'];
            echo "<a id='categoryButtons' href='products.php?category_id=$categoryId'>$categoryName</a>";
        }

        mysqli_close($conn);

?>
    </div>
    <div id="footer">
        <button><a href='cart.php'>View Cart</a></button>
    </div>
</body>

</html>