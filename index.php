<?php
require_once "config.php";
require_once "session.php";
$error= '';
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['submit'])) {
  $email = trim($_POST['email']);
  $password = trim($_POST['password']);
  // validate if email is empty
  if (empty($email)){
    $error .= '<p class="error">Please enter your password.</p>';
  }
  // validate if password is empty
  if (empty($password))
  {
    $error .='<p class="error">Please enter your password.</p>';
  } 
  if (empty($error)) 
  {
    if($query = $db->prepare("SELECT * FROM users WHERE email = ?")) 
    {
      $query->bind_param('s', $email);
      $query->execute();
      $row = $query->fetch();
      if ($row) 
      {
        if (password_verify{$password, $row['password'])) 
        {
          $_SESSION["userid"] = $row['id'];
          $_SESSION["user"] = $row;
          // Redirect the user to welcome page
          header("location: welcome.php");
          exit;
        } 
        else 
        {
          $error .= '<p class="error">The password is not valid.</p>';
        }
        } else 
        {
          $error .= '<p class="error">No User exists with that email address.</p>';
        }
      }
      $query->close();
    }
    // Close connection
    mysquli_close($db);
  }    
?>


<!DOCTYPE html>
<html lang="en">
<head>
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
  background-color: #038c7f;
  padding: 30px;
  text-align: center;
  font-size: 35px;
  color: white;
}

/* Container for flexboxes */
section {
  display: -webkit-flex;
  display: flex;
}

/* Style the navigation menu */
nav {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  background: #ccc;
  padding: 20px;
}

/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}

/* Style the content */
article {
  -webkit-flex: 3;
  -ms-flex: 3;
  flex: 3;
  background-color: #f1f1f1;
  padding: 10px;
}

/* Style the footer */
footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
}

/* Responsive layout - makes the menu and the content (inside the section) sit on top of each other instead of next to each other */
@media (max-width: 600px) {
  section {
    -webkit-flex-direction: column;
    flex-direction: column;
  }
}
</style>
</head>
<body>
<header>
  <img src="https://i.imgur.com/8hOALvw.png" width="500" height="125"/>
</header>

<section>
    <article>
      <h2>Login</h2>
      <p>Please fill in your email and password</p>
      <form action="" method="post">
        <div class="form-group">
          <label>Email Address</label>
          <input type="email" name="email" class="form-control" required/><br><br>
        </div>
        <div class="form-group">
          <label>Password</label>
          <input type="password" name="password" class="form-control" required><br><br>
        </div>
        <div class="form-group">
          <input type="submit" name="submit" class="btn btn-primary" value="Submit"><br><br>
        </div>
        <p style="font-size: 12px">Don't have an account? <a href="https://bryantran21.github.io/csc_3380/register.html">Click here</a>.</p>
      </form>
  </article>
</section>



</body>
</html>
