<!DOCTYPE html>
<html>
	<head>
	    <title>Products Information</title>
	    <link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body class="product_class_body">

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

<?php
		include "config.php";
		session_start();

		if(!$_SESSION["LoggedIn"]) {
			header('location:index.php');
		}

		$product_id = $_GET['productId'];
		$query = "SELECT * FROM product WHERE id = $product_id";
		$result = mysqli_query($conn, $query);

	 	while ($row = mysqli_fetch_assoc($result)){
			$product_name = $row['name'];
			$product_price = $row['price'];
			$product_description = $row['description'];
			$product_image = $row['photo_url'];
			$product_image_path = $row['photo_path'];
			$product_quantity = $row['quantity'];

			
			if(!empty($row['photo_url'])){
				echo "<img class = 'product_class_img'src=".$product_image." alt=".$product_name.">";
			} else{
				echo "<img class = 'product_class_img'src=".$product_image_path." alt=".$product_name.">";
			}
			echo "<h2 class = 'product_class_name' style='font-size: 30px;'>".$product_name."</h2>";
			echo "<p class = 'product_class_description'>".$product_description."</p>";
			echo "<h2 class = 'product_class_price'> $".$product_price."</h2>";
			echo "<h4 class = 'product_class_price'> Available: ".$product_quantity."</h4>";
			

			echo '
				<form method="post" action=>
					<input class = "product_class_quantity" type="number" min="1" max="'.$product_quantity.'" name="quantity" value="1" placeholder="Enter Quantity"/>
					<br><br><br>
					<input type="hidden" name="product_id" value="'.$product_id.'">
					<input class = "product_class_cart" type="submit" name="add_to_cart" value="Add to Cart">
				</form>
			';	
	 	}

	if(isset($_POST['add_to_cart'])){
		 if($_POST['quantity'] > 0){
		 	if (isset($_SESSION['cart'])){
				$_SESSION['cart'][$product_id] = $_POST['quantity'];
				header("location: home.php");

		 	} else {
				$_SESSION['cart'] = array();
				$_SESSION['cart'][$product_id] = $_POST['quantity'];
				header("location: home.php");
			}
	 	}
	}
?>

	</body>
</html>