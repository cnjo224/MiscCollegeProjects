/*
* Author: Caitlin Jones
* Date: September 14, 2018
* Project: Exercise 1 Part 1
* References: https://codereview.stackexchange.com/questions/36837/finding-divisors-of-a-number
*
* */

package part1;
import java.util.ArrayList;

public class Part1 {
    /*
        Function determines if the specified number is abundant
        Returns boolean. True == abundant
    */
    public static boolean abundant(int testNumber){

        //Initialize an ArrayList to hold the divisors of the testNumber
        ArrayList<Integer> divisors = new ArrayList<>();

        //Populate the ArrayList with divisors (excluding the testNumber)
        for(int incr = 1; incr <= testNumber/2 ; incr++){
            if( testNumber % incr == 0){
                divisors.add(incr);
            }
        }

        //Sum the divisors from the ArrayList
        int sum = 0;
        for(int iterator : divisors){
            sum += iterator;
        }

        //Check if sum is greater than testNumber (abundant) and return boolean
        if(sum > testNumber){
            return true;
        }
        else{
            return false;
        }
    }

    /*
        Function determines which number to test
        Outputs results from abundant function
    */
    public static void main(String[] args) {
        //initialize a variable to the first odd number
        int testNumber = 1;
        System.out.println("List of Abundant Numbers < 10000:");

        //Iterate through the odd numbers less than 10 thousand
        while((testNumber += 2) < 10000) {
            //call the abundant function.
            boolean isAbundant = abundant(testNumber);
            //if abundant, output the number, else continue.
            if (isAbundant) {
                System.out.println(testNumber);
            }
        }
    }
}
