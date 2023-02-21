<?php
    include '../config.php';
    session_start();

    $admin_id = $_SESSION['admin_id'];

    if(!isset($admin_id)){
        header('location:admin_login.php');
    }
    
    if(isset($_POST['submit'])){    
        $name = $_POST['productName'];
        $price = $_POST['productPrice'];
        $descrip = $_POST['productDesc'];
        $quantity = $_POST['productQuantity'];
        $productURL = '';
        if(isset($_POST['productURL'])){
            $productURL = $_POST['productURL'];
        }
        if(isset($_FILES['productImage'])){
            $photo= $_FILES['productImage']['name'];
            $file_tmp_name = $_FILES['productImage']['tmp_name'];
            $file_error = $_FILES['productImage']['error'];

            if ($file_error === 0) {
                move_uploaded_file($file_tmp_name, "../images/{$photo}");
                $path = 'images/'.$photo;

                $sqlQuery = "INSERT INTO `product` (`name`, `price`, `description`, `quantity`, `photo_path`, `photo_url`)
                VALUES('$name', '$price', '$descrip','$quantity', '$path', '$productURL')";

                if($conn->query($sqlQuery) == TRUE ){ 
                    $message[] = 'Product added successfully !';
                }else{
                    $message[] = 'something went wrong';
                }
            } 
        } else {

            $sqlQuery = "INSERT INTO `product` (`name`, `price`, `description`, `quantity`, `photo_url`)
            VALUES('$name', '$price', '$descrip','$quantity', '$productURL')";

            if($conn->query($sqlQuery) == TRUE ){ 
                $message[] = 'Product added successfully !';
            }else{
                $message[] = 'something went wrong';
            }
        }
        
        $conn->close();
    }
?>

<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>add new product</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="admin_style.css">
        <script src="adminLogin.js" defer> </script>
    </head>
    <body>
        <section class = 'form-container'>
            <form action='' method='post' enctype="multipart/form-data">
                <?php
                    if(isset($message)){
                        foreach($message as $message){
                            echo '
                                <div class = "message">
                                    <span>'.$message.'</span>
                                    <i class = "fas fa times" onclick="this.parentElement.remove();"></i>
                                </div>
                            ';
                        }
                    }
                ?>
                <h3>Add new product</h3>
                <input type="text" name="productName" required placeholder="Product name" maxlength="60"  class="box" >
                <textarea id="productDescribe" name="productDesc" required placeholder=" Description" rows="3" cols="50" class="box"></textarea>
                <input type="number" name = "productPrice" required placeholder="Price" min="1" step="any" class="box"/>
                <input type="number" name = "productQuantity"  required placeholder="Quantity" class="box" />
                <span id='imageLabel'>Product Image:</span>
                <input srtyle="" type="file" id="productImage" name="productImage" accept="image/jpg, image/jpeg, image/png, image/webp" class="box">
                <input type="text" id="productURL" name="productImage" placeholder="Image URL" class="box" maxlength="255">
                <input type="submit" value="add" class="btn" name="submit" >
            </form>
        </section>
    </body>
</html>