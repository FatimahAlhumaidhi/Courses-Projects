<?php
require_once "config.php";
// session_start();
// session_destroy();

session_start();
$_SESSION['cart'] = array();

if(isset($_POST['submit'])) {

    $email = mysqli_real_escape_string($conn, $_POST['email']);
    $pass = md5($_POST['password']);
    $select = " SELECT * FROM customer WHERE email = '$email' AND password = '$pass' ";
    $result = mysqli_query($conn, $select);

    if(mysqli_num_rows($result) == 1) {  

        $_SESSION["LoggedIn"] = TRUE;
        $res = $result->fetch_assoc();
        $_SESSION["UserID"] = $res['customer_id'];
        $_SESSION["userEmail"] = $res['email'];
        $_SESSION["username"] = $res['name'];
        header('location:home.php'); 

    } else {
      $_SESSION['LoggedIn'] = FALSE;
      $error[] = 'incorrect email or password!';
    }
}
?>

<!DOCTYPE html>
<html>
  <head>
    <title>index</title>
    <link rel="stylesheet" href="admin/admin_style.css">
    <script  type="text/javascript" src="project.js" defer></script>
  </head>
  <body>
    <h1 style="text-align:center" >Video Games E-Store<h1>
      <section class = 'form-container'>
      <form method="post" name="login" action="" onSubmit="return validatelogin()">
      <h3>Login Now</h3>
           <?php
          if(isset($error)){
             foreach($error as $error) {
              echo '<span class="error-msg">'.$error.'</span>';   
             };
          };         
          ?> 
        <h5 style="text-align:start">Email</h5>
        <input type="text" name="email" id="email" class="box" placeholder="enter your email"/><br> <span id='email_error'>  </span> <br>         
        <h5 style="text-align:start">Password</h5>
        <input type="text" name="password" id="password" class="box" placeholder="enter your password"/><br> <span id='password_error'>  </span> <br> 
        <input type="submit" name="submit" class="btn" value="Login Now" >         
        <p>don't have an account?<a href="register.php"> register now</a></p>   
      </form>
      </div>
  </body>
</html>