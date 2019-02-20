<?php
	session_start();
?>
<html>
<head>
  <title>Order History</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<?php
	function OrderHistory($conn) {
		$UserID = $_SESSION['UserID'];
		$sql = "SELECT O.Cost, O.Qty, B.Title FROM Books B, Orders O WHERE B.ISBN = O.ISBN AND O.UserID = '$UserID'";
		if($results = mysqli_query($conn, $sql)) {
			if(mysqli_num_rows($results)>0){
				echo "<table cellpadding=1 border=1 width=75%>";
				echo "<br/></br>";
				echo "<h2 align='center'>Your Current Orders Are Shown Below!</h2>";
				echo "<p>Results!</p>";
				while($row = mysqli_fetch_assoc($results)){
					echo "<tr>";
					echo "<td><font face='Monospace, Monospace, Monospace, Monospace'>Book: $row[Title] Qty: $row[Qty] Cost: $row[Cost]</font></td>";					
				}
				echo "</table>";
			} else if(mysqli_num_rows($results)==0) {
				echo "</br>No results";
			} else {
				echo "Error: ".mysqli_error($conn);
			}
		}
	}		

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

	if(!$conn) {
		echo "Not Connected...";
	} else {
	        OrderHistory($conn);
    	}
    	mysqli_close($conn);
?>
<br/><br/>Return Home!
<ul class='navbar'>
	<li><a href='./indexdb.php'>Home Page</a></li>
</ul>
<br/><br/>
</body>
</html>	
