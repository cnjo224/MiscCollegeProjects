<?php
	session_start();
	if(isset($_SESSION['Cart'])) {
	} else {
		$_SESSION['Cart'] = array();
	}
	if(isset($_SESSION['Email'])) {
	} else {
		$_SESSION['Email'] = "";
		$_SESSION['Type'] = "";
	}
?>
<html>
<head>
  <title>Home</title>
</head>
<body>
<style type="text/css">
body {
 background-color: grey;
}
</style>
<!-- Main content -->
<h1 align="center">Welcome to DaBestdb homepage!</h1>
<center><img src="DaBest.png" width="200" height="125"></center>
<p>DaBestdb, where books are already in your hands!</p>
    <p><img src="homepage.jpg" alt="The Best" height="250" width="250" align ="bottom"></p>
<?php 
	if($_SESSION['Email'] == "") {
		echo "<ul class='navbar'>";
			echo "<li><a href='./Signin.php'style='color: #cc0000'>Sign in/Sign up</a></li>";
			echo "<li><a href='./Search.php'style='color: #cc0000'>Search</a></li>";
			echo "<li><a href='./About Us.php'style='color: #cc0000'>About Us</a></li>";
		echo "</ul>";
	} else if($_SESSION['Email'] != "" && $_SESSION['Type'] == 'Manager') {
		echo "<ul class='navbar'>";
			echo "<li><a href='./Logout.php'style='color: #cc0000'>Logout</a></li>";
			echo "<li><a href='./Search.php'style='color: #cc0000'>Search</a></li>";
			//echo "<li><a href='./Review.php'style='color: #cc0000'>Rate a book!</a></li>";
			echo "<li><a href='./RetrieveOrderHistory.php'style='color: #cc0000'>View Orders</a></li>";
			echo "<li><a href='./AssignMan.php'style='color: #cc0000'>Assign Users</a></li>";
			echo "<li><a href='./ManageDatabase.php'style='color: #cc0000'>ManageDatabase</a></li>";
			echo "<li><a href='./About Us.php'style='color: #cc0000'>About Us</a></li>";
			echo "<li><a href='./ViewShoppingCart.php'style='color: #cc0000'>Shopping Cart</a></li>";
		echo "</ul>";
	}else if($_SESSION['Email'] != "" && $_SESSION['Type'] != 'Manager') {
		echo "<ul class='navbar'>";
			echo "<li><a href='./Logout.php'style='color: #cc0000'>Logout</a></li>";
			echo "<li><a href='./Search.php'style='color: #cc0000'>Search</a></li>";
			//echo "<li><a href='./Review.php'style='color: #cc0000'>Rate a book!</a></li>";
			echo "<li><a href='./RetrieveOrderHistory.php'style='color: #cc0000'>View Orders</a></li>";
			echo "<li><a href='./About Us.php'style='color: #cc0000'>About Us</a></li>";
			echo "<li><a href='./ViewShoppingCart.php'style='color: #cc0000'>Shopping Cart</a></li>";
		echo "</ul>";
	}
?>

<p>It lacks images, but at least it has style.
And it has links, even if they don't go
anywhere&hellip;

<p>There should be more here, but I don't know
what yet.

<!-- Sign and date the page, it's only polite! -->
<address>Made 7 April 2018<br>
  with dabestdb group.</address>

</body>
</html>
