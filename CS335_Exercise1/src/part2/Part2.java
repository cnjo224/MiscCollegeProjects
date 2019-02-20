/*
* Author: Caitlin Jones
* Date: September 14, 2018
* Project: Exercise 1 Part 2
* References: https://codereview.stackexchange.com/questions/36837/finding-divisors-of-a-number
* */

package part2;

import java.util.Arrays;

public class Part2 {
    //Determine whether the number is prime, if not return false
    public static boolean isPrime(int index){
        //Determine if the number has divisors (after 1)
        for(int incr = 2; incr <= index/2 ; incr++){
            //if a divisor is found, stop and return false == not prime number
            if( index % incr == 0){
                return false;
            }
        }
        //if no divisors are found, return true == is prime number
        return true;
    }

    //Find prime numbers with Sieve of Eratosthenes method
    public static void main(String[] args) {
        //initialize a boolean Array with all values TRUE
        boolean[] boolArray = new boolean[10000];
        Arrays.fill(boolArray, Boolean.TRUE);

        //0 and 1 are prime by default. Start at index 2
        //index correlates to the number tested
        for(int index = 2; index < boolArray.length; index++ ){
            //if the number is not prime, set it and its multiples to false
            if(isPrime(index) == false){
                //set multiples of index to false
                int multiplier = 1;
                int multiple = index * multiplier;
                //continue multiplying until the end of array
                while(multiple < boolArray.length){
                    boolArray[multiple] = false;
                    multiple = index * (multiplier++);
                }
            }
        }

        //Output 10 last prime index numbers in array
        int outputNumber = 0;
        for(int index = boolArray.length-1 ; index >= 0; index--){
            //Stop when 10 are output
            if(outputNumber >= 10){
                break;
            }
            //if the element indicates prime number, output the index
            if(boolArray[index] == true) {
                System.out.println(index);
                outputNumber++;
            }
        }

    }
}
