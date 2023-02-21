<?php

    include '../config.php';

    session_start();

    $admin_id = $_SESSION['admin_id'];

    if(!isset($admin_id)){
        header('location:admin_login.php');
    }

    

?>

<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="admin_style.css">
        <script src="adminLogin.js" defer> </script>
    </head>
    <body>
        <section class = 'dashboard'>
            <h1 class="heading">dashboard</h1>
            <div class = 'box-container'>
                <br>
                <div class="box">
                    <img src = 'addIcon.png' >
                    <a href="addNewProduct.php" class="btn">Add new product</a>
                </div>
                <div class="box">
                    <img src = 'orders.png'>
                    <a href="viewOrders.php" class="btn">view orders</a>
                </div>
                <br>
            </div>
            <br>
            <br>

            <div class = 'box-container'>
            <br>
                    <a href="adminLogOut.php" class="delete-btn">Logout</a>
            <br>
            </div>
        </section>
    </body>
</html>