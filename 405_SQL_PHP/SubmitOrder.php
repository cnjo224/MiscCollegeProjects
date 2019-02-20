<?php
	session_start();
?>
<html>
 <head>
  <title>Submit Order</title>
 </head>
 <body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<h1 align='center'>Your order information is below!</h1>
 <?php
	//Functions to handle searches 
	function Order($conn, $Count) {
		$BillCity = $_POST['BillCity'];
		$BillState = $_POST['BillState'];
		$BillStr = $_POST['BillStr'];
		$BillZIP = $_POST['BillZIP'];
		$ShipCity = $_POST['ShipCity'];
		$ShipState = $_POST['ShipState'];
		$ShipStr = $_POST['ShipStr'];
		$ShipZIP = $_POST['ShipZIP'];
		$CardNum = $_POST['CardNum'];
		$TotalCost = ($_SESSION['Cart'][$Count]['Price'] * $_SESSION['Qty'][$Count]);
		$Date = $_SESSION['Date'];
		$Email = $_SESSION['Email'];
		$ISBN = $_SESSION['Cart'][$Count]['ISBN'];
		$Qty = $_SESSION['Qty'][$Count];
		$UserID = $_SESSION['UserID'];

	        $sql = "INSERT INTO Orders(BillCity, BillState, BillStr, BillZIP, CardNum, Cost, Date, Email, ISBN, Qty, ShipCity, ShipState, ShipStr, ShipZIP, Status, UserID) VALUES('$BillCity', '$BillState', '$BillStr', '$BillZIP', '$CardNum', '$TotalCost', '$Date', '$Email', '$ISBN', '$Qty', '$ShipCity', '$ShipState', '$ShipStr', '$ShipZIP', 'Processing', '$UserID')";
		if($BillCity == "" || $BillState == "" || $BillStr == "" || $BillZIP == "" || $ShipCity == "" || $ShipState == "" || $ShipStr == "" || $ShipZIP == "" || $CardNum == "" || $TotalCost == "" || $Date == "" || $Email == "" || $ISBN == "" || $Qty == "" || $UserID == "") {
			echo "<h1>Order could not be created!</h1>";
			echo "<h2>Required Fields were left blank!</h2>";
			echo "<ul class='navbar'>";
			echo "<li><a href='./ViewShoppingCart.php'>Try Again</a></li>";
			echo "</ul>";
		} else if(mysqli_query($conn, $sql)) {
			echo "<h1>Your order has been recieved!</h1>";
			echo "<ul class='navbar'>";
			echo "<li><a href='./indexdb.php'>Home Page</a></li>";
			echo "</ul>";
		} else {
			echo "<h1>Order could not be created!</h1>";
			echo "<h2>Server side error encountered!</h2>";
			echo "Error: ".mysqli_error($conn);
		}
	}
	
	

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

	if(!$conn) {
		echo "Not Connected...";
	   } else {
		$N = count($_SESSION['Cart']);
		for($i=0; $i < $N; $i++) {
			Order($conn, $i);
		}
    	}
    	mysqli_close($conn);	
?>
<br/><br/>Return Home!
 <!-- Site navigation menu -->
 <ul class="navbar">
    <li><a href="./indexdb.php">Home page</a>
 </ul>
</body>
</html>
