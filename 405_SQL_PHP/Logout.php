<?php
	session_start();
?>
<html>
<head>
  <title>Logged Out!</title>
</head>
<body>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<h1 align='center'>You Are Now Logged Out!</h1>
<?php
	echo "<p align='center'><a href='./indexdb.php'>Home page</a></p>";
	$_SESSION['Email'] = "";
	$_SESSION['Password'] = "";
	$_SESSION['UserID'] = "";
	$_SESSION['Type'] = "";
	unset($_SESSION['Cart']);
?>
</body>
</html>

