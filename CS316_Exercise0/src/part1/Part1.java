/*
* Author: Caitlin Jones 12000262
* Date: Aug 31, 2018
* Project: CS 335 Exercise 00 Part 1
* Objective: Simulate rolling two 6-sided dice a number of times
*   and determine how many pairs of snake eyes were generated.
* Input: None
* Output: A table of the number of rolls and the number of snake
*   eyes generated per batch of rolls
* */
package part1;
//import the random library for generating the result of the dice roll
import java.util.Random;

/*Purpose: Simulate rolling the dice. Compare the values to determine
    if it's snake eyes. Count the number of snake eyes.
*/
public class Part1 {
    public static int RollDice(int rollTimes){
        //Initialize variables and random number object
        Random rand = new Random();
        int dieOne;
        int dieTwo;
        int numSnakeEyes = 0;

        //for the number of rolls desired
        for(int number = 0; number < rollTimes; number++){
            //Generate two random numbers between 1 and 6 inclusive
            dieOne = rand.nextInt(6) + 1;
            dieTwo = rand.nextInt(6) + 1;
            /*Compare the numbers generated. If they are both
                equal to 1, increment the counter by 1
            */
            if(dieOne == 1 && dieTwo == 1 ){
                numSnakeEyes++;
            }
        }
        //return the number of snake eyes generated
        return numSnakeEyes;
    }

    // Purpose: Main function
    public static void main(String[] args){
        //Initialize an array of numbers to transfer to RollDice function
        int myArray[] = {10,100,1000,10000};
        //Iterate through the array and find the number of snake eyes generated for each number of rolls
        for(int numInArr = 0; numInArr < myArray.length ; numInArr++){
            //call the RollDice function and output the results
            System.out.println(myArray[numInArr] + ": " + RollDice(myArray[numInArr]) + " snake eyes") ;
        }
    }
}
