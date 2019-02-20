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

//Completely unnecessary class for a non-Functional GUI
//I stumbled around with this, otherwise I would have done it differently
public class CalButton extends JButton {

    private String name;

    public CalButton(String customName){
        //initialize constructor with customName
        super();
        name = customName;
        super.setName(name);
        super.setText(name);
    }

    //instead of getName, it's returnName
    public String returnName(){
        return name;
    }

} //end of CalButton class
