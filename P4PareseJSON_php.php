<?php
#Author: Caitlin Jones
#Project: CS316 Project 4 PHP
#Date: 11/19/18
#Notes: If searching for a string (aka motivation) must put "" around value in text box.
#Resources: https://www.w3resource.com/php/function-reference/is_array.php
	#   https://www.w3schools.com/html/html_styles.asp
	#   https://www.w3schools.com/tags/tag_u.asp
	#   https://stackoverflow.com/questions/1082353/how-do-i-make-text-bold-in-html

//This function searches each record of the json file. Calls print_found when a valid record is found
function parse_nest($data, $term, $crit,$line){
	foreach($data as $key => $value){
		//if the value is a string, then check if it matches the search query
		if(!is_array($value) && $key == $term && $value == $crit){
			echo "<p>";
			//if matches, print the entire line
			print_found($line);
			echo "</p>";
		}
		else{	
			//if it's an array, use recursion to search inside it for the search query
			parse_nest($value, $term, $crit,$line);
		}
	}//End foreach
}//End parse_nest()

//This function takes the approved json line and prints it to the html screen
function print_found($data){
        foreach($data as $key => $value){
		if(!is_array($value)){
			//if the value is a string, echo it
                        echo $key.": ".$value."</br>";
                }
		else{
			//if it's an array, use recursion to print it's insides
                	print_found($value);
                }
	}//End foreach
}//End print_found()

//This function takes the name of a json file and returns it's decoded data
function get_Json($location){
	//check if file exists
	if(file_exists($location)){
		//if it exists, then get the data
		$fileContents = file_get_contents($location);
		$data = json_decode($fileContents,true);
		//if the json object is invalid, then output an error
		if(json_last_error() != 0){
			echo "<h3 style='color:red;'>Error: json object invalid</h3>";
			$data = 1;
		}
		//otherwise return the valid data (or error code 1)
		return $data;
	}
	else{
		echo "<h3 style='color:red;'>Error: file not found. Contact administrator</h3>";
		return 1;
	}
}//End get_Json()

//Makes an array into a dropdown list
function display_array($list){
	foreach($list as $key => $str){
		echo '<option value="'.$str.'">'.$str.'</option>';	
	}	
}//End display_array()

$dataSources = get_Json("DataSources.json");

//CHECK FOR ERROR STATE FROM DataSources.json FILE
if($dataSources != 1){// not an error

#populate the arrays for reference later
$categories = [];//will not contain file names
$searchTerms = [];
foreach($dataSources as $key => $str){
	if($key == 'categories'){ //want the key of the key-value pair
		foreach($str as $arr => $value){
			array_push($categories, $arr);
		}
	}
	if($key == 'searchterms'){ //want the value of the key-value pair
		foreach($str as $arr => $value){
			array_push($searchTerms,$value);
		}
	}
}//End foreach loop

?>

<!--Generate the HTML form, jumping to php to generate the dropdown lists-->
<html>
<head><title>Search!</title>
</head><body><h1> Search This </h1>
<div>	
	<form action="Jones_p4.php"  method="get">
		<div>
			Category:
			<select name="category">
			<?php
				//Populate the dropdown values
				display_array($categories);
			?>
			</select>
			</br></br>
		</div><div>
			Search Term:
			<select name="whichfield">
			<?php
				//Populate the dropdown values
				display_array($searchTerms);
			?>
			</select>
			</br></br>
		</div><div>
			Criteria:
			<input type="text" name="findme">
			</br></br>
		</div><div>
			<input name='submit' type="submit" value="submit">
		</div>
	</form>
</div>
</body>
</html>
<!--Form is finished. Everything else is output below it.-->

<?php
if(isset($_GET['whichfield']) && isset($_GET['category'])
	&& isset($_GET['findme']) && !empty(trim($_GET['findme']))){

	echo "<h4><u>RESULTS FOR:</u></br>Category = ".$_GET['category']."</br>Term = ".$_GET['whichfield']."</br>Criteria = ".$_GET['findme'].".</h4>";

	#get the json file from $dataSources and get the contents of the json to parse later
	foreach($dataSources as $field => $line){
		if($field == 'categories'){
			foreach($line as $ln => $file){
				if($ln == $_GET['category']){
					$data = get_json($file);
				}
			}//end inner foreach
		}
	}//end foreach
	
	if($data != 1){ //Not an error for searchable json
		#parse the specific json file for the input fields
		//foreach array after heading array (each line), search for values
        	foreach($data as $key => $value){
	                foreach($value as $key2 => $value2){
                        	parse_nest($value2,$_GET['whichfield'], $_GET['findme'], $value2);
                	}//end inner foreach
		}//end outer foreach
	}//End searchable json error state
}//End if isset

}//END ERROR STATE CHECK FOR DataSources.json FILE
?>
