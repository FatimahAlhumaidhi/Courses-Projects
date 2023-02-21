<?php
    include '../config.php';
    session_start();

    if(isset($_POST['submit'])){
        $username = $_POST['username'];
        $pass = $_POST['pass'];

        $sqlQuery = "SELECT * FROM `admin_dat` WHERE username = '".$username."' AND password = '".$pass."'";
        $result = $conn->query($sqlQuery);

        if($result->num_rows > 0){ 
            $_SESSION['admin_id'] = 'admin'; 
            $message[] = 'admin logged in successfully';
            header('location:dashboard.php');
            if($pass == 'admin123'){
              header('location:adminReset.php');
            }
        }else{
            $message[] = 'incorrect user name or password';
        }
    }
    $conn->close();
?>

<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>login</title>
        <link rel="stylesheet" href="admin_style.css">
        <script src="adminLogin.js" defer> </script>
    </head>
    <body>
        <section class = 'form-container'>
            <form action ="" method = "post">
                <h3>Admin Login</h3>
                <input type="text" name="username" required placeholder="username" maxlength="20"  class="box" oninput="this.value = this.value.replace(/\s/g, '')">
                <input type="password" name="pass" required placeholder="password" maxlength="20"  class="box" oninput="this.value = this.value.replace(/\s/g, '')">
                <input type="submit" value="login" class="btn" name="submit">
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
            </form>
        </section>
    </body>
</html>