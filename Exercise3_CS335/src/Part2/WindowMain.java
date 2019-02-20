/*
Author: Caitlin Jones
Date: Oct 5, 2018
Project: CS 335 Exercise 3 Part 2
Notes: It works!!
    Based on Dr Seales's LineMidpoint Example code
 */
package Part2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowMain extends JFrame{
    //initialize variables for GUI and polygon
    private Container c;
    private drawPanel drawPane;

    private int x[] = {200,250,100,300,150};
    private int y[] = {100,300,200,200,300};
    private Polygon star = new Polygon(x,y,5);
    private boolean isDragging = false;

    //class constructor
    public WindowMain(){
        //initialize the frame and panels
        super("Exercise 3");
        c = getContentPane();

        drawPane = new drawPanel(star,x,y);
        drawPane.addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                //when the user clicks and holds, set isDragging to true
                if(drawPane.clickInPoly2(e.getPoint())){
                    isDragging = true;
                }
            }
            //when user releases mouse button, set isDragging to false
            public void mouseReleased(MouseEvent e){isDragging = false;}
            //These other methods are necessary to satisfy the compiler
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseClicked(MouseEvent e){}
        });
        drawPane.addMouseMotionListener(new MouseMotionListener() {
            //while a valid yellow box is being dragged, repaint for smooth motion
            public void mouseDragged(MouseEvent e) {
                if(isDragging){
                    int element = drawPane.intPolyClicked(e.getPoint());
                    if(element > -1) {
                        //modify the star poly
                        x[element] = e.getX();
                        y[element] = e.getY();
                        drawPane.setPoly(new Polygon(x, y, 5),x,y);
                        drawPane.repaint();
                    }
                }
            }
            //This method is necessary to satisfy the compiler
            public void mouseMoved(MouseEvent e) {}
        });

        c.add(drawPane, BorderLayout.CENTER);

        setSize(400,400);
        setVisible(true);
    } //End of WindowMain() constructor

    //Initialize the window
    public static void main(String args[]){
        WindowMain wm = new WindowMain();
        wm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }//End of Main
}
