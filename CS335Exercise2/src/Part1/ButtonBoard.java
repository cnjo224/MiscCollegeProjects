/*
* Author: Caitlin Jones
* Date: September 28, 2018
* Project: CS 335 Exercise 2 Part 1
* Objective: Create a calculator (non-functioning GUI)
* Input: None
* Output: GUI
* */
package Part1;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonBoard {
    private CalButton CB[];
    String[] ButtonNames = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};

    //create buttons with actionListeners
    public ButtonBoard(ActionListener AL) {
        CB = new CalButton[ButtonNames.length];

        for(int i = 0; i < ButtonNames.length; i++){
            CalButton c = new CalButton(ButtonNames[i]);
            c.addActionListener(AL);
            CB[i] = c;
        }
    } // end ButtonBoard()

    public void fillBoardView(JPanel view){
        //add each button to the JPanel
        for(CalButton btn : CB){
            view.add(btn);
        }
    } // end fillBoardView
} //end ButtonBoard class
