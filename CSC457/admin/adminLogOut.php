<!DOCTYPE>
<html>
    <head></head>

    <body>
    <?php
        include 'config.php';
        session_start();

        if(isset($_SESSION["admin_id"]) && $_SESSION["admin_id"]) {
            session_unset();
            session_destroy();
            echo '<script>alert("Logged out")</script>';
            header('location:admin_login.php');
            exit;
        } else {
            echo '<script>alert("You are not Logged In")</script>';
            session_unset();
            header('location:admin_login.php');
            exit;
        }
    ?>
    </body>

</html>