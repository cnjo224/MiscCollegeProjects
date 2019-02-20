/*
Author: Caitlin Jones
Date: October 5, 2018
Project: CS335 Exercise 3 Part 1
Notes: This is the WindowMain class;
    Based on Dr Seales's LineMidpoint example code
    Must hit Stop button before Reset button to view changes accurately
 */
package Part1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowMain extends JFrame{
    //initialize GUI elements
    private Container c;
    private drawPanel drawPane;
    private JPanel btnPanel;
    private JButton stopBtn, resetBtn;

    //initialize original star and helper variables
    private int x[] = {200,250,100,300,150};
    private int y[] = {100,300,200,200,300};
    private Polygon star = new Polygon(x,y,5);
    private int theta = 0;
    private Timer time;
    private Point clickPoint = new Point(200,200);

    //Constructor for WindowMain class
    public WindowMain(){
        //create the frame and panels
        super("Exercise 3 Part 1");
        c = getContentPane();
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout());
            stopBtn = new JButton("Stop");
            stopBtn.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   //if the user clicks the Stop button then stop the timer
                   time.stop();
               }
            });
            resetBtn = new JButton("Reset");
            resetBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //if the user clicks the reset button, call resetPane and repaint
                    drawPane.resetPane(star);
                    repaint();
                }
            });
        btnPanel.add(stopBtn);
        btnPanel.add(resetBtn);

        //initialize timer and action listener, repaint on every tick of the timer
        time = new Timer(500, new ActionListener(){
           public void actionPerformed(ActionEvent e){
               drawPane.setClick(clickPoint);
               drawPane.setTheta((theta++));
               repaint();
           }
        });

        //initialize a new drawPanel and start the timer when the panel is clicked
        drawPane = new drawPanel(star,x,y);
        drawPane.addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                clickPoint = e.getPoint();
                time.start();
            }
            public void mouseReleased(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseClicked(MouseEvent e){}
        });

        c.add(btnPanel,BorderLayout.NORTH);
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

} //End of class
