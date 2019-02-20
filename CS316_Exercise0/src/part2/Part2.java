/*
* Author: Caitlin Jones 12000262
* Date: Aug 31, 2018
* Project: CS 335 Exercise 0 Part 2
* Objective: Determine how many terms are required in the alternating
*   infinite series to approximate the value of pie
* Input: None
* Output: The number of terms required and the calculated value
* */
package part2;

public class Part2 {
    //Checks whether the 8 precision digits are correct in the estimation
    public static boolean CheckValue (double approxValue){
        //initialize variables and convert approximation to string
        //approximation is multiplied by 4 because pi/4 = 1 - 1/3...
        String strValue = Double.toString((4.0* approxValue));
        final String PI = "3.14159265";
        boolean isGoodApprox = false;

        //for the 8 decimal digits and the integer and decimal point
        for(int places = 0; places < 10; places++){
            //determine if the calculated value is the same as the known value
            if(strValue.charAt(places) == PI.charAt(places)){
                isGoodApprox = true;
            }
            else{
                //if the approximated value isn't correct, the stop and return false
                isGoodApprox = false;
                break;
            }
        }
        return isGoodApprox;
    }

    //MAIN FUNCTION
    public static void main(String[] args) {
        //Output a little message for user sanity
        System.out.println("RUNNING PROGRAM");
        //Initialize variables
        double approxValue = 1.0 ;
        int termsRequired = 0 ;
        double termDenominator = 3.0;
        boolean plusFlag = false;

        //while the approximated value isn't correct
        do {
            /*increment the number of terms
              determine if the next term is added or subtracted in the series
              add/subtract the term
              reset the plusFlag boolean for the next iteration
              increment the term's denominator for the next iteration.
            */
            termsRequired++;
            if(plusFlag == true) {
                approxValue += (1 / termDenominator);
                plusFlag = false;
            }else{
                approxValue -= (1.0 / termDenominator);
                plusFlag = true;
            }
            termDenominator += 2.0;
        }while(CheckValue(approxValue) == false);

        /*once the approximated value of pi is correct to 8 digits of precision,
          output the approximation and the number of terms used.
        */
        System.out.println("\nThe pi approximation is: " + (4*approxValue) ) ;
        System.out.println("The number of terms required is: " + termsRequired);
        //FOR GRADER: the number of iterations is usually about 155 million
    }
}