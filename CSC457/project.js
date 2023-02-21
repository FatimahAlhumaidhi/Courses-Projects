function validateregistration() {

    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var pass = document.getElementById("password").value;
    var confirmpass = document.getElementById("Cpassword").value;
    var validdata = true;

    if(name == null || name == "") {
        document.getElementById("name_error").textContent = "Please enter your name!";
        document.getElementById("name_error").style.color = "red";
        validdata = false;
    } else { 
        document.getElementById("name_error").textContent = "";
    } 
    if(email == null || email == "") {
        document.getElementById("email_error").textContent = "Please enter your email address!";
        document.getElementById("email_error").style.color = "red";
        validdata = false;
    } else { 
       var mailformat = /^(?:[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&amp;'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;
       if(! mailformat.test(email)) {
          document.getElementById("email_error").textContent = "Please enter a valid email address!";
          document.getElementById("email_error").style.color = "red";
          validdata = false;
        } else { 
        document.getElementById("email_error").textContent = "";
        } 
    } 
    if(pass == null || pass == "") {
        document.getElementById("password_error").textContent = "Please enter password!";
             document.getElementById("password_error").style.color = "red";
        validdata = false;
    } else { 
        document.getElementById("password_error").textContent = "";
    } 
    if(confirmpass == null || confirmpass == "") {
        document.getElementById("confirmpassword_error").textContent = "Please enter password confirmation!";
         document.getElementById("confirmpassword_error").style.color = "red";
        validdata = false;
    } else { 
        if(pass != confirmpass) {
           document.getElementById("confirmpassword_error").textContent = "Password and password confirmation do not match!";
               document.getElementById("confirmpassword_error").style.color = "red";
           validdata = false;
        } else { 
          document.getElementById("confirmpassword_error").textContent = "";
        }
    };  
    return (validdata);
}

function validatelogin() {

    var email = document.getElementById("email").value;
    var pass = document.getElementById("password").value;
    var validdata = true;

    if(email == null || email == "") {
        document.getElementById("email_error").textContent = "Please enter your email address!";
           document.getElementById("email_error").style.color = "red";
        validdata = false;
    } else { 
        document.getElementById("email_error").textContent = "";
    } 
    if(pass == null || pass == "") {
        document.getElementById("password_error").textContent = "Please enter password!";
        document.getElementById("password_error").style.color = "red";
        validdata = false;
    } else { 
        document.getElementById("password_error").textContent = "";
    };  
    return (validdata);
}
