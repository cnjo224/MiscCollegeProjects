<?php
	session_start();
?>
<html>
<head>
 <title>Registered</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<?php

	//This function handles inserting a new user 
	function Register($Gender, $Password, $FirstName, $MiddleName, $LastName, $Email, $Age, $conn){
		$sql = "INSERT INTO Users(Type, Gender, Password, FirstName, MiddleName, LastName, Email, Age)
		VALUES('Customer', '$Gender', '$Password', '$FirstName', '$MiddleName','$LastName','$Email','$Age')";
		if($Gender == "" || $FirstName == "" || $MiddleName == "" || $LastName == "" || $Email == "" || $Age == "") {
			echo "<h1 align='center'>Account could not be created!</h1>";
			echo "<h2 align='center'>Required Fields were left blank!</h2>";
			echo "<p align='center'><a href='./indexdb.php'>Home page</a></p>";
			echo "<p align='center'><a href='./Add_User.php'>Try Again</a></p>";
		} else if(mysqli_query($conn, $sql)) {
			echo "<h1 align'center'>Your account has been sucessfully created!</h1>";
			echo "<h2 align 'center'>Welcome $FirstName $LastName!</h2>";
			echo "<p align='center'><a href='./indexdb.php'>Home page</a></p>";
		} else {
			echo "<h1 align='center'>Account could not be created!</h1>";
			echo "<h2 align='center'>Server side error encountered!</h2>";
			echo "Error: ".mysqli_error($conn);
		}
   	 }

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

    	if(!$conn) {
		echo "Not Connected...";
   	} else{
       		Register($_POST["Gender"],$_POST["Password"], $_POST["FirstName"], $_POST["MiddleName"], $_POST["LastName"], $_POST["Email"], $_POST["Age"], $conn);
    	}
	mysqli_close($conn);
?>
</body>
</html>
