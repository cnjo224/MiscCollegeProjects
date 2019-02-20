#!/usr/bin/node
/*
Author: Caitlin Jones
Date: 10/29/18
Project: CS 316 Project 3 NodeJS
Resources:
    https://nodejs.org/api/http.html
*/

//Initialize constants and requirements
const LOWERPORT = 2000;
const UPPERPORT = 35000;

var http = require("http"),
    url = require('url');

const hostname = 'iris.cs.uky.edu';
const port = Math.floor(Math.random() * (UPPERPORT - LOWERPORT + 1)) + LOWERPORT ; 

//create the server and listener
var server = http.createServer(serveURL);
server.listen(port,hostname, ()=>{
	//output the hostname and port
	console.log("Server Started. Connection at: http://" + hostname + ":" + port);
});

//process requests from user via browser
function serveURL(request, response) {
    //get the url from the request and output it
    var xurl = request.url;
    response.statusCode = 200;
    console.log("The requested URL: ("+xurl+")");
    
    //Use regular expressions to validate URL format
    var comicRegEx = /^\/COMIC\/([1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$|CURRENT$)/;
    var searchRegEx = /^\/SEARCH\/[a-zA-Z0-9/s]+/;
    var fileRegEx = /^\/MYFILE\/[a-zA-Z0-9_]+.html$/;

    //output if the URL is valid, call the appropriate function
    if(xurl.match(comicRegEx)){
        console.log(xurl + " is Valid");
	    giveComic(xurl, response);
    }	
    else if(xurl.match(searchRegEx)){
	    console.log(xurl + " is Valid");
	    doSearch(xurl, response);
    }
    else if(xurl.match(fileRegEx)){
	    console.log(xurl + " is Valid");
	    giveFile(xurl,response);
    }
    else{
	    console.log(xurl + " is Bad");
	    response.end("Error: Invalid URL: " + xurl);
    }
} //End of serveURL()

//parse the url and create a valid dilbert curl url, call callExec()
function giveComic(xurl, response){
    //7 is the index of the char after /COMIC
    var criteria = xurl.substring(6);
    var finalURL = "http://dilbert.com";
    if(criteria != "/CURRENT"){
        finalURL += "/strip/"+criteria;
    }
    callExec(finalURL, response);
}//End of giveComic()

//parse the url for the search criteria and create a curl url, call callExec()
function doSearch(xurl, response){
    //8 is the index of the char after /SEARCH/
    var criteria = xurl.substring(8);
    var finalURL = "https://duckduckgo.com/html/?q=" + criteria + "&ia=web";
    callExec(finalURL, response);
}//End of doSearch()

//parse the file name from the url and create a pathAddress, output the file to the browser
function giveFile(xurl,response){
    //8 is the index of the char after /MYFILE
    var criteria = xurl.substring(7);
    //all files must be in the private directory
    var pathAddress = "./private" + criteria
    var fs = require('fs');
    //output file contents or an error message
    fs.readFile(pathAddress, 'utf8', function read(error, data){
        if(error){
            response.write("403 Error: " + error);
        }
        response.end(data);
    });
}//End of giveFile()

//use the exec call to call the curl command with the URL and output the response.
function callExec(URL, response){
    var exec = require('child_process').exec;
    exec('curl ' + URL, {env: {'PATH': '/usr/bin'}}, function(error, stdout, stderr) {
	if (error) {
	    console.error('exec error'+error);
    }
	//output the requested information.
	response.end(stdout);
    });
}//End of callExec()
