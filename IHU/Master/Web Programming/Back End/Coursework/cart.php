<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <h2 id='headers'>QuickMart Cart</h2>
    <title>QuickMart | Cart</title>
</head>

<body>
    <?php
if(isset($_COOKIE['cart'])) {
    $cart = json_decode($_COOKIE['cart'], true);
    
    if(count($cart) > 0) {

        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "OrderAppDB";

        $conn = mysqli_connect($servername, $username, $password, $dbname);

        if(!$conn) {
            die("Connection failed: " . mysqli_connect_error());
        }

        $totalPrice = 0;

        foreach($cart as $product_id => $quantity) {
            $query = "SELECT * FROM products WHERE product_id = $product_id";
            $result = mysqli_query($conn, $query);

            if($result) {
                $row = mysqli_fetch_assoc($result);
                $product_name = $row['product_name'];
                $product_price = $row['product_price'];

                $totalProductPrice = $product_price * $quantity;

                echo "<div class='cartProducts'>";
                echo "Product Name: $product_name";
                echo "</br>";
                echo "Quantity: $quantity";
                echo "</br>";
                echo "Price: €$product_price";
                echo "</br>";
                echo "Total: €" . number_format($totalProductPrice, 2);
                echo "<form method='post'>";
                echo "<input type='hidden' name='product_id' value='$product_id'>";
                echo "<button class='removeButton' type='submit' name='remove'>Remove</button>";
                echo "</form>";
                echo "</div>";

                $totalPrice += $totalProductPrice;

            } else {
                echo "Query failed: " . mysqli_error($conn);
            }
        }

        echo "<p id='totalPrice'>Cart Total: €" . number_format($totalPrice, 2) . "</p>";

        if (isset($_POST['remove'])) {
            $remove_product_id = $_POST['product_id'];

            if (isset($cart[$remove_product_id])) {
                unset($cart[$remove_product_id]);

                $cart_json = json_encode($cart);
                setcookie('cart', $cart_json, 0, "/");

                header("Location: cart.php");
                exit();
            }
        }
        echo "<div id='footer'>";
        echo "<button><a href='index.php'>QuickMart</a></button>";
        echo "</div>";
    } else {
        echo "<p id='emptyCart'>You Cart is Empty</p>";
        echo "<div id='footer'>";
        echo "<button><a href='index.php'>QuickMart</a></button>";
        echo "</div>";
    }
} else {
    echo "<p id='emptyCart'>You Cart is Empty</p>";
    echo "<div id='footer'>";
    echo "<button><a href='index.php'>QuickMart</a></button>";
    echo "</div>";
}
    ?>
</body>

</html>