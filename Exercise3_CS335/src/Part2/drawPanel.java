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
import java.util.ArrayList;

public class drawPanel extends JPanel {

    // coordinates for midpoint algorithm
    private int x_cord1;
    private int x_cord2;
    private int y_cord1;
    private int y_cord2;
    //this is the yellow squares marking the points of the star
    private ArrayList <Polygon> polyArr = new ArrayList<Polygon>();
    int[] starX, starY;

    // polygons for drawing
    private Polygon poly; //Red star

    //constructor to draw the original polygon
    public drawPanel(Polygon poly, int[] x, int[] y){
        this.poly = poly;
        setBackground(Color.BLACK);
        starX = x;
        starY = y;
        //create an array of all the small yellow boxes
        for(int i=0;i< x.length;i++){
            int [] tempx = {x[i]-5,x[i]+5,x[i]+5,x[i]-5};
            int[] tempy = {y[i]-5,y[i]+5,y[i]+5,y[i]-5};
            Polygon temp = new Polygon(tempx,tempy, 4);
            polyArr.add(temp);
        }
    }// End drawPanel constructor

    // Helper Functions for determining which yellow box was clicked
    //return T/F if yellow poly was clicked inside
    public boolean clickInPoly2(Point click){
        if(whichPolyClicked(click) != null){
            return true;
        }
        return false;
    }// End clickInPoly2

    //return the yellow polygon clicked
    public Polygon whichPolyClicked(Point click) {
        for(int i= 0; i < polyArr.size(); i++){
            if(polyArr.get(i).contains(click)){
                return polyArr.get(i);
            }
        }
        return null;
    }// End whichPolyClicked

    //return the index of the yellow polygon clicked
    public int intPolyClicked(Point click){
        for(int i = 0; i < polyArr.size();i++){
            if(polyArr.get(i).contains(click)){
                return i;
            }
        }
        return -1;
    }//End intPolyClicked

    //set a new polygon and reinitialize the polyArr
    public void setPoly(Polygon poly, int[] x, int[] y){
        this.poly = poly;
        starX = x;
        starY = y;
        polyArr.clear();
        //create an array of all the small yellow boxes
        for(int i=0;i< x.length;i++){
            int [] tempx = {x[i]-5,x[i]+5,x[i]+5,x[i]-5};
            int[] tempy = {y[i]-5,y[i]+5,y[i]+5,y[i]-5};
            Polygon temp = new Polygon(tempx,tempy, 4);
            polyArr.add(temp);
        }
    } //End setPoly

    //where the drawing happens
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor (Color.RED);
        Graphics2D picture = (Graphics2D)g;
        picture.setStroke(new BasicStroke(3));

        //Use midpoint alg to draw the poly
        draw_poly_by_bres (picture);

        // draw the small yellow poly, which is the yellow "handle" on the drag point
        draw_small_poly (picture);
    } //End paintComponent

    //draw all 5 yellow boxes
    public void draw_small_poly (Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.setStroke(new BasicStroke(1));
        //initialize the polyArr of yellow polygon square
        for(int i = 0; i < starY.length;i++){
            int [] tempx = {starX[i]-5,starX[i]+5,starX[i]+5,starX[i]-5};
            int [] tempy = {starY[i]-5,starY[i]-5,starY[i]+5,starY[i]+5};
            polyArr.set(i,(new Polygon(tempx,tempy, 4)));
            g.drawPolygon(polyArr.get(i));
        }
    } //End draw_small_poly

//All below was directly from LineMidpoint example code
    // draw all the lines of the poly using the Midpoint Alg
    public void draw_poly_by_bres (Graphics2D g) {
        for (int i=0; i<(poly.npoints-1); i++) {
            x_cord1 = poly.xpoints[i];
            x_cord2 = poly.xpoints[i+1];
            y_cord1 = poly.ypoints[i];
            y_cord2 = poly.ypoints[i+1];
            draw_line_by_bres(g);
        }
        x_cord1 = poly.xpoints[0];
        x_cord2 = poly.xpoints[poly.npoints-1];
        y_cord1 = poly.ypoints[0];
        y_cord2 = poly.ypoints[poly.npoints-1];
        draw_line_by_bres(g);
    } //End draw_poly_by_bres

    // draw a single line segment using Midpoint alg
    public void draw_line_by_bres (Graphics2D g) {

        float m;

        int dy=Math.abs(y_cord2-y_cord1);
        int dx=Math.abs(x_cord2-x_cord1);
        m=(float)dy/(float)dx;

        if(m<=1)
            slope_less_1(g);
        else
            slope_great_1(g);
    } //End draw_line_by_bres


    //    slope less than 1

    public void slope_less_1 (Graphics g) {
        int x = x_cord1, y = y_cord1 , p = 0,xEnd=0,yEnd=0;
        int dx,dy;
        float m;

        plotpoints(x,y, g);
        dx = (x_cord1-x_cord2);
        dy = (y_cord1-y_cord2);
        m=(float)dy/(float)dx;

        dx = Math.abs(x_cord1-x_cord2);
        dy = Math.abs(y_cord1-y_cord2);
        p = 2 * dy - dx;
        if(x_cord1>x_cord2)
        {
            x=x_cord2;
            y=y_cord2;
            xEnd = x_cord1;
        }
        else
        {	x=x_cord1;
            y=y_cord1;
            xEnd= x_cord2;
        }
        plotpoints (x, y, g);
        while (x < xEnd)
        {
            x++;
            if (p < 0)
                p = p + 2 * dy;
            else {
                if (m < 0) y--;
                else y++;
                p = p + 2*(dy - dx);
            }
            plotpoints (x, y, g);
        }
    }//End slope_less_1


    public void slope_great_1 (Graphics g) {
        int x = x_cord1, y = y_cord1 , p = 0,xEnd=0,yEnd=0;
        int dx,dy;
        float m;

        plotpoints(x,y, g);
        dx = (x_cord1-x_cord2);
        dy = (y_cord1-y_cord2);
        m=(float)dy/(float)dx;

        dx = Math.abs(x_cord1-x_cord2);
        dy = Math.abs(y_cord1-y_cord2);
        p = 2 * dx - dy;
        if(y_cord1>y_cord2)
        {
            x=x_cord2;
            y=y_cord2;
            yEnd = y_cord1;
        }
        else
        {	x=x_cord1;
            y=y_cord1;
            yEnd= y_cord2;
        }
        plotpoints (x, y, g);
        while (y < yEnd)
        {
            y++;
            if (p < 0)
                p = p + 2 * dx;
            else {
                if (m <0 ) x--;
                else x++;
                p = p + 2*(dx - dy);
            }
            plotpoints (x, y, g);
        }
    } //End slope_great_1

    // Helper method to draw a single point
    public void  plotpoints(int x, int y, Graphics g) {
        g.fillRect(x , y , 1,1);
    } //End plotpoints
}
