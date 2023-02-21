<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "estore";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$json = file_get_contents('customers.json');
$data = json_decode($json, true);

foreach ($data as $customer) {
    $full_name = $customer['name'];
    $email = $customer['email'];
    $password = md5($customer['password']);

    $sql = "INSERT INTO customer (name, email, password) VALUES ('$full_name', '$email', '$password')";

    if ($conn->query($sql) === TRUE) {
        echo "New customer record created successfully <br>";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

$json = file_get_contents('games.json');
$data = json_decode($json, true);

foreach ($data as $game) {
    $name = $game['name'];
    $price = $game['price'];
    $description = $game['description'];
    $quantity = $game['quantity'];
    $photo_url = $game['photo_url'];
    $photo_path = $game['src_photo'];
    $quantity = mt_rand(0, 100);

    $sql = "INSERT INTO product (name, price, description, quantity, photo_url, photo_path) VALUES ('$name', '$price', '$description', '$quantity', '$photo_url', '$photo_path')";

    if ($conn->query($sql) === TRUE) {
        echo "New game record created successfully <br>";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

$conn->close();
?>
