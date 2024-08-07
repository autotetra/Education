<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "OrderAppDB";

$conn = mysqli_connect($servername, $username, $password, $dbname);

if(!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

if(isset($_GET['category_id'])) {
    $category_id = $_GET['category_id'];
    $productQuery = "SELECT * FROM products WHERE product_active = 1 AND product_category_id = $category_id";
    $productResult = mysqli_query($conn, $productQuery);

    $categoryIdQuery = "SELECT * FROM categories WHERE category_id = $category_id";
    $categoryResult = mysqli_query($conn, $categoryIdQuery);
    $categoryIdRow = mysqli_fetch_assoc($categoryResult);
    $category_name = $categoryIdRow['category_name'];
}

?>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>QuickMart | <?php echo htmlspecialchars($category_name) ?>
    </title>
</head>

<body>
    <h2 id='headers'>Products</h2>

    <?php
while($productRow = mysqli_fetch_assoc($productResult)) {
    $product_name = $productRow['product_name'];
    $product_price = $productRow['product_price'];
    echo "<div id='categoryProductsList'>";
    echo "<p>Name: $product_name</p>";
    echo "<p>Price: €$product_price</p>";
    echo "<a class='addToCart' href='insert_cart.php?product_id={$productRow['product_id']}'>Add to Cart</a>";
    echo "</div>";

}
?>

    <div id='footer'>
        <button><a href="index.php">QuickMart</a></button>
        <button><a href="cart.php">View Cart</a></button>
    </div>
</body>

</html>