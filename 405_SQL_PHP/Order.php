<?php
	session_start();
?>
<html>
<head>
 <title>Order</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<p>Enter your order information is below!</p>
<form action="SubmitOrder.php" method="post">
<?php
	echo "Please fill in the Billing Information!<br>";
	echo "Billing City: <input type='text' name='BillCity'><br>";
	echo "Billing State: <input type='text' name='BillState'><br>";
	echo "Billing Street: <input type='text' name='BillStr'><br>";
	echo "Billing ZIP: <input type='text' name='BillZIP'><br><br>";

	echo "Please fill in the Shipping Information!<br>";
	echo "Shipping City: <input type='text' name='ShipCity'><br>";
	echo "Shipping State: <input type='text' name='ShipState'><br>";
	echo "Shipping Street: <input type='text' name='ShipStr'><br>";
	echo "Shipping ZIP: <input type='text' name='ShipZIP'><br><br>";
	
	echo "Please enter your card number<br>";
	echo "Card Number: <input type='text' name='CardNum'><br><br>";

	$_SESSION['Date'] = date('Y-m-d H:i:s');
	$_SESSION['Qty'] = $_POST['Qty'];
	//Calculate the cost of the order
	$TotalCost = 0;
	
	$N = count($_SESSION['Cart']);
	for($i=0; $i < $N; $i++) {
		$TotalCost = $TotalCost + ($_SESSION['Cart'][$i]['Price'] * $_POST['Qty'][$i]);
	}
	echo "<br/><br/>Total Cost = $$TotalCost";
	
	echo "<button name='Type' type='submit' value='Customer'>Order Now</button></br></br>";
?>
Return Home!<br/>
<!-- Site navigation menu -->
<ul class="navbar">
	<li><a href="./indexdb.php">Home page</a>
	<li><a href="./Search.php">Search</a>
	<li><a href="./About_Us.php">About Us</a>
</ul>
<br/><br/>
</body>
</html>
	   	    
