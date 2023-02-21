<?php
    include '../config.php';
    session_start();

    $admin_id = $_SESSION['admin_id'];

    if(!isset($admin_id)){
        header('location:admin_login.php');
    }
    
    if(isset($_POST['submit'])){
        $password = $_POST['password'];
        $confirm = $_POST['confirm'];

        if ($password == $confirm) {

            $sqlQuery = "UPDATE `admin_dat` SET `password`= '".$password."' WHERE admin_id = 1";
            if($conn->query($sqlQuery) == TRUE ){ 
                $message[] = 'Password updated successfully';
                header('location:admin_login.php');
            }else{
                $message[] = 'something went wrong';
            }
            
        }
        else{
            $message[] = 'Password not matched';
        }
    }
?>

<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
        <link rel="stylesheet" href="admin_style.css">
        <script src="adminLogin.js" defer> </script>
    </head>
    <body>
        <section class = 'form-container'>
            <form action ="" method = "post">
                <h3>Reset Password</h3>
                <p>first time to login you should reset your password</p>
                <input type="paaword" name="password" required placeholder="password" maxlength="20"  class="box" oninput="this.value = this.value.replace(/\s/g, '')">
                <input type="password" name="confirm" required placeholder="confirm password" maxlength="20"  class="box" oninput="this.value = this.value.replace(/\s/g, '')">
                <input type="submit" value="Reset now" class="btn" name="submit">
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