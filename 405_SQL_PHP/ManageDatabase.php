<?php
	session_start();
	if(!isset($_SESSION['NumOfRows'])) {
		$_SESSION['NumOfRows'] = 0;
	} 
?>
<html>
<head>
  <title>Manage Database</title>
</head>
<body>
<style type="text/css">
body {
	background-color: grey;
}
</style>
<center><img src="DaBest.png" width="300" height="175"></center>
<form action="ManageDatabase.php" method="post">
<h1 align='center'>Welcome to the Managment Page</h1>
<?php
	if(!isset($_POST['Action'])) {
		echo "What would you like to do?";
		echo "<button name='Action' type='submit' value='Add'>Add book</button>";
		echo "<button name='Action' type='submit' value='Update'>Update book</button>";
		echo "<button name='Action' type='submit' value='Delete'>Delete Book</button>";
	} else if($_POST['Action'] == 'Delete' || $_POST['Action'] == 'Update') {
		$_SESSION['CurrentAction'] = $_POST['Action'];
		echo "What book would you like to delete/update?<br/><br/>";
		echo "Search by author: <input type='text' name='author'><br>";
		echo "Search by book title: <input type='text' name='title'><br>";
		echo "Search by ISBN: <input type='text' name='ISBN'><br>";
		echo "Search by subject: <input type='subject' name='subject'><br>";
		echo "Search by keyword: <input type='text' name='keyWords'><br>";
		echo "</div>";
		echo "<button name='Action' type='submit' value='Search'>Search</button>";
	} else if($_POST['Action'] == 'Search') {
		$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
		if(!$conn) {
			echo "Not Connected...";
		} else {
		        Search($conn);
    		}
    		mysqli_close($conn);
	} else if($_POST['Action'] == 'FinalDelete') {
		$Boxes = $_POST['CheckBoxes'];
		if(empty($Boxes)) {	
			echo("<p>You didn't select any books to add to your cart!</p>\n");
		} else {	
		    if($N = count($Boxes) > 1) {
			    echo "You selected more than 1 box";
		    } else {
			    $conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
			    if(!$conn) {
				echo "Not Connected...";
			    } else {
		       		$_SESSION['ISBN'] = $Boxes[0];
				    Delete($conn);
			    }
    		}
    		mysqli_close($conn);
		}
	} else if($_POST['Action'] == 'Add') {
		AddDetails();
	} else if($_POST['Action'] == 'FinalAdd') {
		$conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
		if(!$conn) {
			echo "Not Connected...";
		} else {
			Add($conn);
    		}
    		mysqli_close($conn);
	} else if($_POST['Action'] == 'FinalUpdate') {
	    if(isset($_SESSION['UpdateFlag']) && $_SESSION['UpdateFlag'] == 0) {
    	    $Boxes = $_POST['CheckBoxes'];
    		if(empty($Boxes)) {	
    			echo("<p>You didn't select any books to add to your cart!</p>\n");
    		} else {	
    		    if($N = count($Boxes) > 1) {
    			    echo "You selected more than 1 box";
    		    } else {
    			    $conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
    			    if(!$conn) {
    				echo "Not Connected...";
    			    } else {
    		       		$_SESSION['ISBN'] = $Boxes[0];
    				    Update($conn);
    				    mysqli_close($conn);
    			    }
        		}
    		}
	    } else if($_SESSION['UpdateFlag'] == 1) {
	        $conn=mysqli_connect("localhost","id5016227_dabestdb","cs405g","id5016227_bookstore");
    		if(!$conn) {
    		    echo "Not Connected...";
    	    } else {
	            Update($conn);
    		    mysqli_close($conn);
    	    }
	    } else {
	        echo "<h1 align='center'>The book was updated</h1>";
	    }
	}
	
	function Update($conn) {
	    if(!isset($_SESSION['UpdateFlag']) || $_SESSION['UpdateFlag'] == 0) {
    	    $sql = "SELECT ISBN, Language, Price, PublishDate, Publisher, QtyStock, Subject, Summary, Title FROM Books WHERE ISBN = $_SESSION[ISBN]";
    	    if($results = mysqli_query($conn, $sql)) {
    		} else {
    			echo "Error: ".mysqli_error($conn);
    		}
    		$row = mysqli_fetch_assoc($results);
    		echo "What would you like to update?<br/><br/>";
    		echo "Update ISBN: <input type='text' name='ISBN' value=$row[ISBN]><br>";
    		echo "Update Language: <input type='text' name='Language' value='$row[Language]'><br>";
    		echo "Update Price: <input type='text' name='Price' value='$row[Price]'><br>";
    		echo "Update PublishDate: <input type='text' name='PublishDate' value='$row[PublishDate]'><br>";
    		echo "Update Publisher: <input type='text' name='Publisher' value='$row[Publisher]'><br>";
    		echo "Update QtyStock: <input type='text' name='QtyStock' value='$row[QtyStock]'><br>";
    		echo "Update Subject: <input type='text' name='Subject' value='$row[Subject]'><br>";
    		echo "Update Summary: <input type='text' name='Summary' value='$row[Summary]'><br>";
    		echo "Update title: <input type='text' name='Title' value='$row[Title]'><br>";
    		echo "</div>";
    		$_SESSION['UpdateFlag'] = 1;
    		echo "<button name='Action' type='submit' value='FinalUpdate'>Update</button>";
	    } else {
	        $sql = "UPDATE Books SET ISBN = '$_POST[ISBN]', Language = '$_POST[Language]', Price = '$_POST[Price]', PublishDate = '$_POST[PublishDate]', Publisher = '$_POST[Publisher]', QtyStock = '$_POST[QtyStock]', Subject = '$_POST[Subject]', Summary = '$_POST[Summary]', Title = '$_POST[Title]' WHERE ISBN = $_POST[ISBN]";
    	    if(mysqli_query($conn, $sql)) {
    	        $_SESSION['UpdateFlag'] = 2;
    		} else {
    			echo "Error: ".mysqli_error($conn);
    		}
	    }
	}

	//Function to handle searches 
	function Search($conn) {
		$Author = $_POST['author'];
		$Title = $_POST['title'];
		$ISBN = $_POST['ISBN'];
		$Subject = $_POST['subject'];
		$Keyword = $_POST['keyWords'];

		$sql = "SELECT B.Title, B.ISBN, B.Price FROM Books B, Author A, Keywords K WHERE B.ISBN = A.ISBN AND B.ISBN = K.ISBN AND (A.Author = '$Author' OR B.Title = '$Title' OR B.ISBN = '$ISBN' OR B.Subject = '$Subject' OR K.Keyword =  '$Keyword') GROUP BY ISBN";
		if($results = mysqli_query($conn, $sql)) {
			if(mysqli_num_rows($results)>0){
			    $_SESSION['UpdateFlag'] = 0;
				echo "Which book would you like to $_POST[Action]</br>";
				echo "<table cellpadding=1 border=1 width=50%>";
				echo "<br/></br>";
				echo "Results </br>";
				while($row = mysqli_fetch_assoc($results)){
					echo "<tr>";
					echo "<td><font face='Monospace, Monospace, Monospace, Monospace'>Title: $row[Title].<br/></font></td>";						
					if($_SESSION['Type'] != "") {
						//Do I really need a value for checkboxes?
						echo "<td>$_SESSION[CurrentAction]<input type='checkbox' name='CheckBoxes[]' value='$row[ISBN]'></td>";
						
					}
				}
				if($_SESSION['CurrentAction'] == 'Delete') {
					echo "<button name='Action' type='submit' value='FinalDelete'>Submit</button>";
				} else if($_SESSION['CurrentAction'] == 'Update') {
					echo "<button name='Action' type='submit' value='FinalUpdate'>Submit</button>";
				}
			} else{
					echo "</br>No results";
				}
			} else {
				echo "Error: ".mysqli_error($conn);
			}
			echo "</table>";
		}
	
	//Function that deletes a book from the database
	function Delete($conn) {
		$sql = "DELETE FROM Books WHERE ISBN = $_SESSION[ISBN]";
		if(mysqli_query($conn, $sql)) {
			echo "Book was successfully removed from the database!";
		} else {
			echo "Error: ".mysqli_error($conn);
		}
	}
	
	//Function that gets details for upcoming add
	function AddDetails() {
		echo "Plese enter the following details for the book you would like to add!</br>";
		echo "ISBN: <input type='text' name='ISBN'><br>";
		echo "Language: <input type='text' name='Language'><br>";
		echo "Price: <input type='text' name='Price'><br>";
		echo "PublisherDate: <input type='subject' name='Date'><br>";
		echo "Publisher (Seperated by ','): <input type='text' name='Publisher'><br>";
		echo "QtyStock: <input type='text' name='QtyStock'><br>";
		echo "Subject: <input type='text' name='Subject'><br>";
		echo "Summary: <input type='text' name='Summary'><br>";
		echo "Title: <input type='subject' name='Title'><br>";
		echo "Authors (Seperated by ','): <input type='text' name='Author'><br>";
		echo "Keywords (Seperated by ','): <input type='text' name='Keywords'><br>";
		echo "</div>";
		echo "<button name='Action' type='submit' value='FinalAdd'>Submit</button>";
	}
	
	//Function that adds a book to the database
	function Add($conn) {
		//Will need to trim both
		$Authors = explode(',', $_POST['Author']);
		$Keywords = explode(',', $_POST['Keywords']);
		$ISBN = $_POST['ISBN'];
		$Language = $_POST['Language'];
		$Price = $_POST['Price'];
		$PublishDate = $_POST['Date'];
		$Publisher = $_POST['Publisher'];
		$QtyStock = $_POST['QtyStock'];
		$Subject = $_POST['Subject'];
		$Summary = $_POST['Summary'];
		$Title = $_POST['Title'];

		//First handle adding the new book to the Books table
		$sql = "INSERT INTO Books(ISBN, Language, Price, PublishDate, Publisher, QtyStock, Subject, Summary, Title) VALUES('$ISBN', '$Language', '$Price', '$PublishDate', '$Publisher', '$QtyStock', '$Subject', '$Summary', '$Title')"; 
		if($ISBN == "" || $Language == "" || $Price == "" || $PublishDate == "" || $Publisher == "" || $QtyStock == ""|| $Subject == "" || $Summary == "" || $Title == "") {	
			echo "Required fields were left blank!";
		} else if(mysqli_query($conn, $sql)) {
			echo "<h2 align='center'>$Title successfully added to the database</h2>";	
		}

		//Next handle updating authors
		$N = count($Authors);
		for($i=0; $i < $N; $i++) {
			if($Authors[$i] != "" && $ISBN != "") {
				$sql = "INSERT INTO Author(Author, ISBN) VALUES('$Authors[$i]', '$ISBN')";
				if(mysqli_query($conn, $sql)) {
				} else {
					echo "Error: ".mysqli_error($conn);
				}
			} else{
				echo "Fields blank!";
			}
		}

		//Next handle updating keywords
		$N = count($Keywords);
		for($i=0; $i < $N; $i++) {
			if($Keywords[$i] != "" && $ISBN != "") {
				$sql = "INSERT INTO Keywords(ISBN, Keyword) VALUES('$ISBN', '$Keywords[$i]')";
				if (mysqli_query($conn, $sql)) {
				} else {
					echo "Error: ".mysqli_error($conn);
				}
			} else{
				echo "Fields blank!";
			}	
		}
	}
//Learn how to place text after a table 
?>
<br/><br/>Return home!
<ul class='navbar'>
	<li><a href='./indexdb.php'>Home Page</a></li>
</ul>
<br/><br/>
</body>
</html>

