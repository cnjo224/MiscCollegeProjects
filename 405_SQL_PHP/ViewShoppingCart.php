<?php
	session_start();
?>
<html>
<head>
  <title>Shopping Cart</title>
 </head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="Order.php" method="post">
<?php
	echo "<h1>Welcome to your shopping cart!</h1>";
	if(count($_SESSION['Cart']) > 0) {
		//$TotalCost = 0;
		//$Count = 0;
		echo "Cart </br>";
		echo "<table cellpadding=1 border=1 width=80%>";
	        echo "<br/>";
	        $N = count($_SESSION['Cart']);
	        for($i=0; $i < $N; $i++) {
			//$TotalCost = $TotalCost + $_SESSION['SearchResults'][$i]['Price'];
			$Title = str_replace('~', '&nbsp;', str_pad($_SESSION['Cart'][$i]['Title'], 20, '~'));
			$Cost = str_replace('~', '&nbsp;', str_pad($_SESSION['Cart'][$i]['Price'], 12, '~'));
			echo "<tr>";	
			echo "<td><font face='Monospace, Monospace, Monospace, Monospace'>Title = ".$Title." Cost = ".$Cost."<br/></font></td>";
			echo "<td>Qty: <input type='text' name=Qty[]</td><br>";
			echo "</tr>";
		}
		echo "</table>";
		echo "<button name='Type' type='submit' value='Customer'>Order Now</button></br></br>";
	} else {
		echo "No books currently in your cart!";
	}
?>
<!-- Site navigation menu -->
<ul class="navbar">
  	<li><a href="./indexdb.php">Home page</a>
	<li><a href="./AssignMan.php">Return to User Assignment page</a>
</ul>
<br/><br/>
</body>
</html>
