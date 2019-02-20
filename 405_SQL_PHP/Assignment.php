<?php
	session_start();
?>
<html>
<head>
 <title>DaBestdb/Assignment</title>
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
	function Assignment($conn) {
		$Flag = 0;
		$String = "";
		$Boxes = $_POST['CheckBoxes'];
		if(empty($Boxes)) {
			echo("<p>You didn't select any boxes.</p>\n");
		} else {
            		$N = count($Boxes);
			for($i=0; $i < $N; $i++){
				if($Flag == 0) {
					$String = $String." UserID = ".$Boxes[$i];
					$Flag = 1;
				} else {
					$String = $String." OR UserID = ".$Boxes[$i];
				}
			}
			echo("</p>");
		}

		echo "</br>";
		if($_REQUEST['Type'] == 'Manager') {
			$sql = "UPDATE Users SET Type = 'Manager' WHERE ".$String;
		} else {
			$sql = "UPDATE Users SET Type = 'Customer' WHERE ".$String;
		}
		if(mysqli_query($conn, $sql)) {
			echo "<h1>New Manager has been Assigned!</h1> ";
		} else {
			echo "Error: ".mysqli_error($conn);
		}
	}
	
	

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

	if(!$conn) {
		echo "Not Connected...";
	} else {
		if($_SESSION['Type'] == 'Manager') {
	        	Assignment($conn);
	        } else {
	        	echo "You do not have the credentials to perform this action";
	        }
    	}
    	mysqli_close($conn);	
?>
<br/><br/>Return Home!
<!-- Site navigation menu -->
<ul class="navbar">
  	<li><a href="./indexdb.php">Home page</a>
	<li><a href="./AssignMan.php">Return to User Assignment page</a>
</ul>
<br/><br/>
</body>
</html>
