/*
* Author: Caitlin Jones
* Date: September 28, 2018
* Project: CS 335 Exercise 2 Part 1
* Objective: Create a calculator (non-functioning GUI)
* Input: None
* Output: GUI
* Note: Based this off the Memory Game template. Could be simplified for this application
* */
package Part1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Part1 extends JFrame implements ActionListener{
    private JPanel outputView, boardView;
    private ButtonBoard Board;
    private JTextArea textOut;

    public Part1() {
        //Call the base class constructor
        super("Calculator GUI");

        //Add the output display area
        Container c = getContentPane();
        outputView = new JPanel();
        outputView.setLayout(new BorderLayout());
        boardView = new JPanel();

        //Add the Button Area
        Board = new ButtonBoard(this);
        boardView.setLayout(new GridLayout(4,4));

        //Initialize the board by adding buttons to it
        Board.fillBoardView(boardView);

        //Initialize the elements on the outputView Panel
        textOut = new JTextArea();
        textOut.setEditable(true);
        outputView.add(textOut);

        //Add the panels to the window
        c.add(outputView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.CENTER);

        setSize(300,400);
        setVisible(true);
    } // end of Part1() constructor

    public void actionPerformed(ActionEvent e){
        //if a button is clicked, get it's text and output it to the textOut field
        CalButton btn = (CalButton)e.getSource();
        textOut.setText("You clicked the " + btn.returnName() + " button.");
    } // end of actionPerformed

    public static void main(String args[]){
        //Initialize the window
        Part1 p1 = new Part1();
        p1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
    } // end of main

} // end of Part1 class
