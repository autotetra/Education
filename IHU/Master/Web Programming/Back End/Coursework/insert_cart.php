<?php

if(isset($_GET['product_id'])) {
    $product_id = $_GET['product_id'];

    if (isset($_COOKIE['cart'])) {
        $cart = json_decode($_COOKIE['cart'], true);

        if (isset($cart[$product_id])) {
            $cart[$product_id]++;
        } else {
            $cart[$product_id] = 1;
        }
    } else {
        $cart = [$product_id => 1];
    }

    $cart_json = json_encode($cart);
    setcookie('cart', $cart_json, 0, "/");

    header("Location: " . $_SERVER['HTTP_REFERER']);
    exit();
}
