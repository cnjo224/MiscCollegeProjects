/*
* Author: Caitlin Jones
* Date: September 28, 2018
* Project: CS 335 Exercise 2 Part 2
* Objective: Use the Sieve of Eratosthenes algorithm to calculate primes.
* Input: User can input number between 1 and 10000 inclusive through GUI
* Output: Label with error message, is prime, or is not prime status
* */
package Part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Part2 extends JFrame implements ActionListener {
    private JPanel textPanel, outPanel;
    private JTextArea txtArea;
    private JButton submitBtn;
    private JLabel outLabel;
    private boolean[] boolArray; //cannot initialize or modify in static function

    public Part2(){
        //Initialize the GUI elements
        super("Sieve Calculator");
        Container c = getContentPane();

        textPanel = new JPanel(); //txtArea input
        textPanel.setLayout(new BorderLayout());

        outPanel = new JPanel(); //buttons and labels

        submitBtn = new JButton();
        submitBtn.setText("Submit");
        submitBtn.addActionListener(this);
        outPanel.add(submitBtn);

        outLabel = new JLabel();
        outLabel.setText(" ");
        outPanel.add(outLabel);

        txtArea = new JTextArea();
        txtArea.setEditable(true);
        textPanel.add(txtArea);

        c.add(textPanel, BorderLayout.CENTER);
        c.add(outPanel, BorderLayout.SOUTH);

        //calculate the primes between 0 and 10000
        calcArray();

        //After primes are identified, Make the GUI visible
        setSize(400,400);
        setVisible(true);
    } // end of Part2 constructor

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
    } //end of isPrime()

    //Find prime numbers with Sieve of Eratosthenes method
    public void calcArray() {
        //In case finding the primes takes a moment, output encouragement
        System.out.println("Computing Primes. Gui will load shortly.");

        //initialize a boolean Array with all values TRUE
        boolArray = new boolean[10001];
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
        } //end of for loop
    } //end of calcArray()

    public static void main(String[] args){
        //Initialize the GUI Window
        Part2 p2 = new Part2();
        p2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
    } // end of main

    public void actionPerformed(ActionEvent e){
        //When the user hits submit, validate the input
        try{
            //parse will throw exception if not an integer
            int num = Integer.parseInt(txtArea.getText());
            //if integer within range, output appropriate message
            if(num >= 1 && num <= 10000){
                //search in boolArray for result, output message
                if(boolArray[num]){
                    outLabel.setText(num + " is Prime.");
                }else{
                    outLabel.setText(num + " is not Prime.");
                }
            }
            else{
                //Number is out of range
                outLabel.setText(num + " Out of Range (1,10000).");
            }
        }catch(NumberFormatException f){
            //parse exception caught
            outLabel.setText("Invalid input. Not a integer.");
        }
    } // end of actionPerformed
} // end of Part2 class

