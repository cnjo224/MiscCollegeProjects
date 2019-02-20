<?php
	session_start();
?>
<html>
<head>
 <title>DaBestdb/Assign Managers</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="Assignment.php" method="post">
<?php
	//Functions to handle searches 
	function Hey($conn) {
		$sql = "SELECT FirstName, MiddleName, LastName, UserID, Type FROM Users";
		if($results = mysqli_query($conn, $sql)) {
			if(mysqli_num_rows($results)>0){
				//$UsersInfo = array();
				echo "<table cellpadding=1 border=1 width=80%>";
				echo "<br/> ";
				echo "Current Users </br>";
				while($row = mysqli_fetch_assoc($results)){
					echo "<tr>";
				    	$first = str_replace('~', '&nbsp;', str_pad($row["FirstName"], 12, '~'));
			        	$middle = str_replace('~', '&nbsp;', str_pad($row["MiddleName"], 12, '~'));
			        	$last = str_replace('~', '&nbsp;', str_pad($row["LastName"], 12, '~'));
					echo "<td><font face='Monospace, Monospace, Monospace, Monospace'>First name: ".$first." Middle name: ".$middle." Last name: ".$last." Type: ".$row["Type"]."<br/></font></td>";
					echo "<td><input type='checkbox' name='CheckBoxes[]' value='$row[UserID]'></td>";
					echo "</tr>";
				}
				echo "</table>";
			} else{
				echo "No results";
			}
		} else {
			echo "Error: ".mysqli_error($conn);
		}
	}
	
	
	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

	if(!$conn) {
		echo "Not Connected...";
	} else {
	       	Hey($conn);
    	}
    	mysqli_close($conn);	
?>
<button name="Type" type="submit" value="Customer">Customer</button>
<button name="Type" type="submit" value="Manager">Manager</button>
</form>
<br/><br/>Return Home!
<!-- Site navigation menu -->
<ul class="navbar">
 	<li><a href="./indexdb.php">Home page</a>

</ul>
</body>
</html>



