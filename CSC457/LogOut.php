<!DOCTYPE>
<html>
    <head></head>

    <body>
    <?php
        include 'config.php';
        session_start();

        if(isset($_SESSION["loggedIn"]) && $_SESSION["loggedIn"]) {
            session_unset();
            session_destroy();
            echo '<script>alert("Logged out")</script>';
            header('location:index.php');
            exit;
        } else {
            echo '<script>alert("You are not Logged In")</script>';
            session_unset();
            header('location:index.php');
            exit;
        }
    ?>
    </body>

</html>