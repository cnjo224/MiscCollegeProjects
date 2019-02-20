<?php
	session_start();
?>
<html>
<head>
 <title>Signed in!</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<?php
	//Functions to handle searches 
	function SignIn($conn) {
		$Email = trim ( $_POST['Email'], " ");
		$Password = trim ( $_POST['Password'], " ");


		$sql = "SELECT Email, Password, UserID, FirstName, LastName, Type FROM Users WHERE Email = '$Email' AND Password = '$Password'";
		if($results = mysqli_query($conn, $sql)) {
			
			if(mysqli_num_rows($results)>0){
				$row = mysqli_fetch_assoc($results);
				$_SESSION['Email'] = $Email;
				$_SESSION['Password'] = $Password;
				$_SESSION['UserID'] = $row['UserID'];
				$_SESSION['Type'] = $row['Type'];
				echo "<h1 align='center'>You Are Now Signed In!</h1>";
				echo "<h2 align ='center'>Welcome ".$row['FirstName']." ".$row['LastName']."!</h2>";
			}
			else {
				echo "<h1 align='center'>No registered account found<h1>";
				echo "</br><h3 align='center'><a href='./Signin.php'>Try again</a></h3>";
			}
		echo "</br><h3 align='center'><a href='./indexdb.php'>Return Home</a></h3>";
		} else {
			echo "Error: ".mysqli_error($conn);
		}
	}

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");

	if(!$conn) {
		echo "Not Connected...";
	} else {
	 	SignIn($conn);
    	}
    	mysqli_close($conn);
?>
</body>
</html>


