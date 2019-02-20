<?php
	session_start();
?>
<html>
<head>
  <title>Reviews</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="Review.php" method="post">
<?php
	//Connect to the database
	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
	if(!$conn) {
		echo "Not Connected...";
   	} else {
		if(isset($_POST['Rating'])) {
			Rate($conn);
		}
   	}	
	
	//Display the rating and comments box
	if(isset($_POST['CheckBoxes'])) {
		echo "<table bgcolor='#f2f2f2' style='padding:50px' align='center'>";
		echo "<form action='' method='post'><tr>";
		echo "<tr><td> Rating : </td><td><input type='text' name='Rating'></td></tr>";
		echo "<tr><td> Comment : </td><td><textarea name='comment' rows='6' cols='50'></textarea></td></tr>";
		echo "<tr><td><button name='Action' type='submit' value='Rate'>Submit Review!</button></td></tr>";
		echo "</form>";
		echo "</table>";
		
		$_SESSION['ISBN'] = $_POST['ISBN'];
	}

	//Function that inserts the review info into the Reviews table
	function Rate($conn) {
		$Comments = $_POST['comment'];
		$ISBN = $_SESSION['ISBN'];
		$Score = $_POST['Rating'];
		$UserID = $_SESSION['UserID'];
		$sql = "INSERT INTO Reviews(Comments, ISBN, Score, UserID) VALUES('$Comments', '$ISBN', '$Score', '$UserID')";
		if(mysqli_query($conn, $sql)) {
			echo "<h1 align='center'>Your review has been accepted!</h1></br>";
		} else {
				echo "Error: ".mysqli_error($conn);
		}
	}
	echo "Return home!";
	echo "<ul class='navbar'>";
		echo "<li><a href='./indexdb.php'>Home Page</a></li>";
	echo "</ul>";
    	mysqli_close($conn);
//Learn how to place text after a table 
?>
