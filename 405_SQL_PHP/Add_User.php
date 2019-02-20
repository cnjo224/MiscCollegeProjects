<?php
	session_start();
?>
<html>
<head>
 <title>Creat New Account</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<h1 align='center'>Create A New Account!</h1>
<form action="Register.php" method="post">
<div>
 First name*: <input type="text" name="FirstName"><br>
 Middle name: <input type="text" name="MiddleName"><br>
 Last name*: <input type="text" name="LastName"><br>
 Gender* (specify M (male), F (female), or O (other)): <input type="text" name="Gender"><br>
 Age: <input type="text" name="Age"><br>
 Email*: <input type="text" name="Email"><br>
 Password*: <input type="password" name="Password"><br>
 Confirm Password*: <input type="password" name="Password"><br>
 <input type="submit">
 </div>
 </form>
<br/><br/>Return Home!
<ul class='navbar'>
	<li><a href='./indexdb.php'>Home Page</a></li>
</ul>
 </body>
</html>
