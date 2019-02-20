<?php
	session_start();
	if(!isset($_SESSION['NumOfRows'])) {
		$_SESSION['NumOfRows'] = 0;
	}
	if(!isset($_SESSION['Type'])) {
	    $_SESSION['Type'] = "";
	}
?>
<html>
<head>
  <title>Search</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="Search.php" method="post">
<h1 align='center'>Welcome to the Search Page!</h1>
Search by author: <input type="text" name="author"><br>
Search by book title: <input type="text" name="title"><br>
Search by ISBN: <input type="text" name="ISBN"><br>
Search by subject: <input type="subject" name="subject"><br>
Price Range: <input type="text" name="Min"><input type="text" name="Max"><br>

Search by key word(s) (separate words by ','): <input type="text" name="keyWords"><br>
</div>
<?php
	//Display buttons and determine form action
	if($_SESSION['Type'] != "") {
		if(isset($_REQUEST['Action'])) {
			if($_REQUEST['Action'] != 'Rate' && $_SESSION['NumOfRows'] > 0) {
				echo "<button name='Action' type='submit' formaction='Search.php' value='Search'>Search</button>";
				echo "<button name='Action' type='submit' formaction='Search.php' value='Order'>Add To Cart</button>";
				echo "<button name='Action' type='submit' formaction='./Review.php' value='Rate'>Rate</button>";
			} else if($_REQUEST['Action'] != 'Rate' && $_SESSION['NumOfRows'] == 0) {
				echo "<button name='Action' onclick='submitForm('Search.php')' type='submit' value='Search'>Search</button>";
			}
		} else {
			echo "<button name='Action' type='submit' formaction='Search.php' value='Search'>Search</button>";
		}
	} else {
		echo "<button name='Action' type='submit' formaction='Search.php' value='Search'>Search</button>";
	}

	$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
	if(!$conn) {
		echo "Not Connected...";
	} else {
		if(isset($_POST["author"]) && $_REQUEST['Action'] == 'Search') {
	        	Search($_POST["author"], $_POST["title"], $_POST["ISBN"], $_POST["subject"], $_POST["keyWords"], $conn);
		} else if(isset($_POST['CheckBoxes']) && $_REQUEST['Action'] == 'Order') {
			AddToCart();
		}
    	}
    	mysqli_close($conn);

	//Function to handle searches 
	function Search($author, $title, $ISBN, $subject, $keyword, $conn) {						
		$Max = (int)$_POST['Max'];
		$Min = (int)$_POST['Min'];
		$SearchResults = array();
		$sql = "SELECT B.Title, B.ISBN, B.Price FROM Books B, Author A, Keywords K WHERE B.ISBN = A.ISBN AND B.ISBN = K.ISBN AND (A.Author = '$author' OR B.Title = '$title' OR B.ISBN = '$ISBN' OR B.Subject = '$subject' OR K.Keyword = '$keyword' OR (B.Price < $Max AND B.Price > $Min)) GROUP BY ISBN";
		if($results = mysqli_query($conn, $sql)) {
			if(mysqli_num_rows($results)>0){
				$_SESSION['NumOfRows'] = mysqli_num_rows($results);
				echo "<table cellpadding=1 border=1 width=50%>";
				echo "<br/></br>";
				echo "Results </br>";
				while($row = mysqli_fetch_assoc($results)){
					echo "<tr>";
					echo "<td><font face='Monospace, Monospace, Monospace, Monospace'>Title: $row[Title].<br/></font></td>";						
					if($_SESSION['Type'] != "") {
						//Do I really need a value for checkboxes?
						echo "<td><input type='checkbox' name='CheckBoxes[]' value='$row[ISBN]'></td>";
						$SearchResults[] = $row;
					}
				}
				echo "</table>";
				$_SESSION['SearchResults'] = $SearchResults;
				} else{
					echo "</br>No results";
				}
			} else {
				echo "Error: ".mysqli_error($conn);
			}
		}

		//Function that adds item to cart
		function AddToCart() {
			$Flag = 0;
			$String = "";
			$Boxes = $_POST['CheckBoxes'];
			if(empty($Boxes)) 
        		{
				echo("<p>You didn't select any books to add to your cart!</p>\n");
			} 
        		else 
        		{
            			$N = count($Boxes);
				for($i=0; $i < $N; $i++)
				{
					echo "</br>You have added ".$_SESSION['SearchResults'][$i]['Title']." to your cart";
					array_push($_SESSION['Cart'], $_SESSION['SearchResults'][$i]);
				}
				echo("</p>");
			}
		}
	
	

//Learn how to place text after a table 
?>
<br/><br/>Return Home!
<ul class='navbar'>
	<li><a href='./indexdb.php'>Home Page</a></li>
</ul>
<br/><br/>
</body>
</html>

