/*
Author: Caitlin Jones
Date: October 5, 2018
Project: CS335 Exercise 3 Part 1
Notes: This is the drawPanel class;
    Based on Dr Seales's LineMidpoint example code
 */

package Part1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

//this is the drawing JPanel
public class drawPanel extends JPanel{

    //initialize variables for rotation and transformation
    int[] starX, starY;
    private Point2D[] pointArr = new Point2D[5];
    private Point2D[] newPointArr = new Point2D[5];

    private Point click = new Point();
    private int theta;

    private Polygon poly; //Red star
    private boolean reset = false;

    //constructor to draw the original polygon
    public drawPanel(Polygon poly, int[] x, int[] y){
        //set the polygon and the color
        this.poly = poly;
        setBackground(Color.BLACK);
        //set these int[] to the x or y points of the star
        starX = x;
        starY = y;
    }

    //reset is always false, unless resetBtn is clicked
    public void resetPane(Polygon star){
        //reset the polygon
        this.poly = star;
        reset = true;
    }

    //Helper functions
    public void setClick(Point click){this.click = click;}
    public void setTheta(int theta){this.theta = theta;}

    //where the drawing happens
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor (Color.RED);
        Graphics2D picture = (Graphics2D)g;
        picture.setStroke(new BasicStroke(3));

        //keep rotating or hold still until resetBtn is clicked
        if(!reset) {
            rotateStar(click, theta); //rotate the star
        }
        else{
           reset = false;
        }
        // use the polygon draw to draw the current star position
        picture.drawPolygon(poly);
    }//End of paintComponent

    //perform the transformation of the star's rotation
    public void rotateStar(Point click, int theta){
        AffineTransform transStar = new AffineTransform();
        //Must convert integer points to doubles for rotate
        for(int i=0; i< poly.xpoints.length; i++) {
            pointArr[i] = new Point2D.Double((double) poly.xpoints[i], (double) poly.ypoints[i]);
        }

        //perform the transformation
        transStar.rotate(theta, click.getX(), click.getY());
        transStar.transform(pointArr,0,newPointArr,0,pointArr.length); //try elliminating newPointArr

        //Convert the transformed points to integers
        int[] tempX = new int[newPointArr.length];
        int[] tempY = new int[newPointArr.length];
        for(int i= 0; i < newPointArr.length; i++){
            tempX[i] = (int)newPointArr[i].getX();
            tempY[i] = (int)newPointArr[i].getY();
        }

        //remake the polygon with the newly transformed numbers
        this.poly = new Polygon(tempX,tempY, tempX.length);
    }

}// End of class
