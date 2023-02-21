<?php
require_once "config.php";

if (isset($_POST['submit'])) {
    $name = mysqli_real_escape_string($conn,$_POST['name']);
    $email = mysqli_real_escape_string($conn,$_POST['email']); 
    $password = md5($_POST['password']);

    $q = " SELECT * FROM customer WHERE email = '$email' ";  
    $result = mysqli_query($conn,$q);
    if(mysqli_num_rows($result)>0) {
         $error[] = 'user already exists';    
    } else {
         $insert="INSERT INTO customer(name,email,password) VALUES( '$name','$email','$password')";
         mysqli_query($conn,$insert);
         header('location:index.php');
     }
};
?>
<!DOCTYPE html>
<html>
  <head>
    <title>register</title>
    <link rel="stylesheet" href="admin/admin_style.css">
    <script  type="text/javascript" src="project.js" defer></script> 
  </head>
  <body>
      <section class="form-container">
      <form method="post" name="register" action="" onSubmit="return validateregistration()">
        <h2>Register now</h2>      
        <?php
          if(isset($error)){
              foreach($error as $error) {
                echo '<span class="error-msg">'.$error.'</span>';   
              };
          };
         ?>
        <h2 style="text-align:start">Name:</h2>
        <input type="text" class="box" name="name" id="name" placeholder="enter your name" ><br> <span id='name_error'><br> </span> <br>       
        <h2 style="text-align:start">Email:</h2>
        <input type="text" class="box" name="email" id="email" placeholder="enter your email"><br> <span id='email_error'>  </span> <br>
        <h2 style="text-align:start">Password:</h2>
        <input type="text" class="box" name="password" id="password" placeholder="enter your password" ><br> <span id='password_error'>  </span> <br>      
        <h2 style="text-align:start">Confirm Password:</h2>
        <input type="text" class="box" name="Cpassword" id="Cpassword" placeholder="confirm your password" ><br> <span id='confirmpassword_error'>  </span> <br>
        <input type="submit" class="btn" name="submit" value="Register Now" class="f-btn">
        <p>already have an account?<a href="index.php"> login now</a></p>  
      </form>
      </div>
  </body>
</html>