/*
*
* Notes: https://stackoverflow.com/questions/20190110/2d-int-array-shuffle
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bombs extends JFrame implements ActionListener{
    private Timer tmr;
    private Container c = getContentPane();
    private JMenuBar menuBar;
    private JLabel timerLabel;
    private JButton smileyButton;
    private JMenu game, help;
    private JMenuItem itemNew, itemSetup, itemExit;
    private JPanel boardPanel;
    private Board gameBoard;
    private int sideValue = 4, bombValue = 4;

    public Bombs(){
        super("Bombs Game");
        //Game menu option
        game = new JMenu("Game");
        itemNew = new JMenuItem("New");
        itemNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //Clear the board and set it's grid to the new size
                resetBoard();
            }
        });

        itemSetup = new JMenuItem("Setup");
        itemSetup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent){
                PopupSetup setup = new PopupSetup();
                setup.addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        sideValue = setup.getNumGridSide();
                        bombValue = setup.getNumBombs();
                        if(setup.performResetBoard()){resetBoard();}
                    }//windowClosing
                });//addWindowListener
            }//actionPerformed
        }); // addActionListener

        itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        game.add(itemNew);
        game.add(itemSetup);
        game.add(itemExit);

        //Help menu option
        help = new JMenu("Help");
        help.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {}
            public void mousePressed(MouseEvent mouseEvent) {
                //Show a help Message
                String text = "How to Play:\nClick a box. A number will appear to indicate nearby bombs.\nIf you click a bomb, the game is over. Win by clicking all the boxes except the bombs.";

                JOptionPane helpFrame = new JOptionPane();
                helpFrame.showMessageDialog(null, text);
            }
            public void mouseReleased(MouseEvent mouseEvent) {}
            public void mouseEntered(MouseEvent mouseEvent) {}
            public void mouseExited(MouseEvent mouseEvent) {}
        });

        //menuBar
        menuBar = new JMenuBar();
        menuBar.add(game);
        menuBar.add(help);

        smileyButton = new JButton("smiley");
        tmr = new Timer();
        tmr.schedule(new );

        boardPanel = new JPanel();
        gameBoard = new Board(sideValue,bombValue, this);
        resetBoard();

        /************/
        c.add(menuBar, BorderLayout.NORTH);
        c.add(boardPanel, BorderLayout.CENTER);
        ///c.add(smileyButton, BorderLayout.CENTER);


        setSize(745, 500);
        setVisible(true);
    } //End Bombs constructor

    public void resetBoard(){
        //Clear the board and set it's grid to the new size
        boardPanel.removeAll();
        boardPanel.revalidate();
        boardPanel.setLayout(new GridLayout(sideValue,sideValue, 2, 0));

        //create and fill the board to the new specs
        gameBoard.initBoard(sideValue, bombValue, true);
        gameBoard.fillBoard(boardPanel);

        //repaint the board
        boardPanel.repaint();

        //Restart the timer
        tmr.restart();
    }

    public void actionPerformed(ActionEvent e) {
        //Main actions for program here
        //get the button effected
        BombButton btn = (BombButton)e.getSource();

        //if the btn was 0, perform clearing
        if(btn.getNeighbors() == 0 && !btn.isBomb()){
            gameBoard.performClearing(btn.getPosY(),btn.getPosX());
        }
        //Show the value of the btn: bomb or neighbor count
        btn.showBomb();

        if(btn.isBomb()){
            //tmr.stop();
            tmr.stop();
            gameBoard.stopGame();
        }
    }//End actionPerformed

    public static void main(String args[]){
        Bombs b = new Bombs();
        b.addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent e){System.exit(0);}
        });
    } // End main

}//End Bombs class
