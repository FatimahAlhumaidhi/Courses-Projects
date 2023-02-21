<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>view orders</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <h1>Orders</h1>
        </br></br>
    <table>
        <tr>
            <th>Order Number</th>
            <th>Order Date</th>
            <th>Customer Email</th>
            <th>Total Price</th>
        </tr>
        <?php
            include '../config.php';
            session_start();

            if(!isset($_SESSION['admin_id'])){
                header('location:admin_login.php');
            }
        
            $admin_id = $_SESSION['admin_id'];
        
            if(!isset($admin_id)){
                header('location:admin_login.php');
            }

            $limit = 10;
            $page = isset($_GET['page']) ? (int)$_GET['page'] : 1;
            $start = ($page - 1) * $limit;

            $query = "SELECT order_date, customer_email, order_number, total_price FROM orders ORDER BY order_date DESC LIMIT $start, $limit";
            $result = mysqli_query($conn, $query);

            while ($order = mysqli_fetch_assoc($result)) {
                echo "<tr>";
                echo "<td>" . $order['order_number'] . "</td>";
                echo "<td>" . $order['order_date'] . "</td>";
                echo "<td>" . $order['customer_email'] . "</td>";
                echo "<td>$" . $order['total_price'] . "</td>";
                echo "</tr>";
            }
        ?>
    </table>
    <div class="pagination">
        <?php
            $query = "SELECT COUNT(*) as num_orders FROM orders";
            $result = mysqli_query($conn, $query);
            $data = mysqli_fetch_assoc($result);
            $total_pages = ceil($data['num_orders'] / $limit);

            if ($page > 1) {
                echo "<a href='viewOrders.php?page=" . ($page - 1) . "'>Previous</a> ";
            }

            for ($i = 1; $i <= $total_pages; $i++) {
                if ($i == $page) {
                    echo $i . " ";
                } else {
                    echo "<a href='viewOrders.php?page=$i'>" . $i . "</a> ";
                }
            }

            if ($page < $total_pages) {
                echo "<a href='viewOrders.php?page=" . ($page + 1) . "'>Next</a>";
            }
        ?>
    </div>
    </body>
</html>