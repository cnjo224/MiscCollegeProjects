<?php
	session_start();
?>
<html>
<head>
 <title>Sign_in</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="SignedIn.php" method="post">
	<p>Please enter your login information below!</p>
	Email: <input type="text" name="Email"><br>
	Password: <input type="password" name="Password"><br>
	<input type="submit" value="Submit" name="Submit"><br><br>
	Don't have an account? <a href="./Add_User.php">Sign up here!</a><br>
	<!-- Site navigation menu -->
	<p>Return Home!</p>
	<ul class="navbar">
		<li><a href="./indexdb.php">Home page</a>
	</ul>
</form>
</body>
</html>
