<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

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
	        <?php
	            include "config.php";
	            session_start();

	            if($_SESSION["LoggedIn"]) {
	                echo "<h4 style='text-align: left; padding: 1rem;'> Hello, ". $_SESSION["username"] . "!</h4>";
	            } else {
	                header('location:index.php');
	            }
	        ?>
	    </nav>
	</div>

    <h1>Available Products</h1>
    <div class="product-container">
        <?php
            
            $limit = 12;
            $page = isset($_GET['page']) ? (int) $_GET['page'] : 1;
            $start = ($page - 1) * $limit;

            $query = "SELECT * FROM product WHERE quantity > 0 ORDER BY id DESC LIMIT $start, $limit";
            $result = mysqli_query($conn, $query);

            while ($product = mysqli_fetch_assoc($result)) {
                $link =  'product.php?productId='.$product['id'];

                echo "<div class='product-block'> <a href='$link' class='btn'>";
                if(!empty($product['photo_url'])){
                    echo "<img class='product-img' src='" . $product['photo_url'] . "'>";
                } else{
                    echo "<img class='product-img' src='" . $product['photo_path'] . "'>";
                }
                echo "<p class='product-name'>" . $product['name'] . "</p>";
                echo "<p class='product-price'>$" . $product['price'] . "</p>";
                if ($product['quantity'] < 5){
                    echo "<p class='note' style='color:Tomato;' > Only " . $product['quantity'] . " left </p>";
                }
                echo "</a> </div>";
            }
        ?>
    </div>
    <div class="pagination">
        <?php
            $query = "SELECT COUNT(*) as num_products FROM product WHERE quantity > 0";
            $result = mysqli_query($conn, $query);
            $data = mysqli_fetch_assoc($result);
            $total_pages = ceil($data['num_products'] / $limit);

            if ($page > 1) {
                echo "<a href='home.php?page=" . ($page - 1) . "'>Previous</a> ";
            }

            for ($i = 1; $i <= $total_pages; $i++) {
                if ($i == $page) {
                    echo $i . " ";
                } else {
                    echo "<a href='home.php?page=$i'>" . $i . "</a> ";
                }
            }

            if ($page < $total_pages) {
                echo "<a href='home.php?page=" . ($page + 1) . "'>Next</a>";
            }
        ?>
    </div>
</body>
</html>