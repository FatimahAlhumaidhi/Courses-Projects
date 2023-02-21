<!DOCTYPE>
<html>
  <head>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Cart</title>
     <link rel="stylesheet" href="style.css" /> 
  </head>

  <body>

    <?php
        include "config.php";
        session_start();

        if(!$_SESSION["LoggedIn"]) {
            header('location:index.php');
        }
        
    ?>

    <?php
      // include 'config.php';
      // session_start();

      if (!isset($_SESSION['cart'])) {
        $_SESSION['cart'] = array();
      }

      if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['Update_quantity'])) {
          $product_id = $_POST['product_id'];
          $quantity = $_POST['quantity'];
  
          $_SESSION['cart'][$product_id] = $quantity;
        }
        if (isset($_POST['delete'])) {
          $product_id = $_POST['product_id'];
          unset($_SESSION['cart'][$product_id]);
        }
        if (isset($_POST['submit_order'])) {

          $date = date("Y-m-d");
          $id = $_SESSION['UserID'];
          $select = " SELECT * FROM customer WHERE customer_id = '$id'";
          $result = mysqli_query($conn, $select);
          $row = mysqli_fetch_assoc($result);
          $email = $row['email'];
          $total_cost = $_SESSION['total_cost'];
          
          $sql = "INSERT INTO orders (customer_email, order_date, total_price) VALUES ('$email','$date', '$total_cost')";
          $result = mysqli_query($conn, $sql);


          foreach ($_SESSION['cart'] as $product_id => $quantity){
            $sql = "SELECT quantity FROM product WHERE id='$product_id'";
            $result = mysqli_query($conn, $sql);
            $row = mysqli_fetch_assoc($result);   
            $oldQuantity = intval($row["quantity"]);
            $newQuantity = $oldQuantity - $quantity;
            $sql = "UPDATE product SET quantity ='$newQuantity' WHERE id='$product_id'";
            $conn->query($sql);
          }
          unset($_SESSION['cart']);
          echo '<h2>Order confirmed!</h2>';
        }
      }
    ?>

    <div class="product_class_header">
      <nav role="header">
        <ul>
          <li>
              <a href="home.php"><img width="5%" class="logoClass" src="logo.png" alt="Logo"></a>
          </li>	
          <li>
            <a href="home.php" class="active">HOME</a>
          </li>	
          <li>	
            <a href="cart.php"> CART</a>
          </li>	
          <li>
            <a href="LogOut.php"> LOG OUT</a>
          </li>
        </ul>
      </nav>
    </div>

  <div class="cart-content">
	<table >
    <tr >
      <th>Product Name</th>
      <th>Price</th>
      <th>Total</th>
      <th>Quantity</th>
    </tr>

    <?php      
        
        $total_cost = 0;
        if(isset($_SESSION['cart'])){
          foreach ($_SESSION['cart'] as $product_id => $quantity) {
              $sql = "SELECT * FROM product WHERE id=$product_id";
              $result = mysqli_query($conn, $sql);
              $row = mysqli_fetch_assoc($result);
              $product_name = $row['name'];
              $productQuantity = $row['quantity'];
              $price = $row['price'];
              $total = $price * $quantity;
              $total_cost += $total;
              ?>

              <tr >
                <td><?php echo $product_name; ?></td>
                <td><?php echo $price; ?></td>
                <td><?php echo $total; ?></td>
                <td>
                  <form action="" method="post">
                    <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
                    <input type="number" name="quantity" min="1" max= "<?php echo $productQuantity; ?>" name="quantity" value="<?php echo $quantity; ?>">
                    <input type="hidden" name="total_cost" value="<?php echo $total_cost; ?>">
                    <input type="submit" value="Update" name="Update_quantity">
                  </form>
                </td>
                <td>
                  <form action="" method="post">
                    <input type="hidden" name="product_id" value="<?php echo $product_id; ?>">
                    <input type="submit" value="Delete" name="delete" style = "background-color:#e74c3c">
                  </form>
                </td>
              </tr>
              
              <?php 
              
          } 
        }
        $_SESSION['total_cost'] = $total_cost;

    
      ?>

  </table>
  <?php echo '<h3>Total: '.$total_cost.'</h3>'; ?>
  </div>

  <form action="" method="post">
    <input class = "product_class_cart" type="submit" value="Submit Order" name="submit_order" class="submit_order">
  </form>
	
  </body>

</html>