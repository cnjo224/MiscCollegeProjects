/*
*Author: Caitlin Jones
*Date: October 19, 2018
*Notes: http://www.java2s.com/Code/Java/Swing-JFC/Aframewithmanyslidersandatextfieldtoshowslidervalues.htm
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PopupSetup extends JFrame{
    private boolean reset = false;
    private int gridSizeValue = 4, bombValue = 4;
    private JPanel topPanel, middlePanel, bottomPanel;
    private JLabel gridSizeLabel, gridSizeValueLabel, bombLabel, bombValueLabel;
    private JSlider gridSizeSlider, bombSlider;
    private JButton button, saveButton, newGameButton;

    public PopupSetup(){
        super("Setup");
        Container sc = getContentPane();

        //*****Top Panel
        topPanel = new JPanel(new GridLayout(1,3));
        sc.add(topPanel, BorderLayout.NORTH);

        String[] butArr = {"Beginner", "Intermediate", "Expert"};
        for(int i = 0; i < butArr.length; i++) {
            button = new JButton(butArr[i]);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tempButton = (JButton)e.getSource();
                    buttonClicked(tempButton.getText());
                }
            });
            topPanel.add(button);
        }


        //*****Middle Panel
        middlePanel = new JPanel();
        sc.add(middlePanel, BorderLayout.CENTER);

        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints gblc = new GridBagConstraints();
        middlePanel.setLayout(gridLayout);

        //row 0
        gridSizeLabel = new JLabel("Grid Size");
        gblc.gridx = 0;
        gblc.gridy = 0;
        gridLayout.setConstraints(gridSizeLabel, gblc);
        middlePanel.add(gridSizeLabel);

        gridSizeSlider = new JSlider();
        gridSizeSlider.setMinimum(3);
        gridSizeSlider.setMaximum(12);
        gridSizeSlider.setValue(gridSizeValue);
        gridSizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                gridSizeValue = source.getValue();
                gridSizeValueLabel.setText(gridSizeValue + " x " + gridSizeValue);

                //bombs must not exceed 1/2 the spaces available
                bombSlider.setMaximum((int)Math.floor((gridSizeValue * gridSizeValue) / 2));
            }
        });
        gblc.gridx = 1;
        gblc.gridy = 0;
        gridLayout.setConstraints(gridSizeSlider, gblc);
        middlePanel.add(gridSizeSlider);

        gridSizeValueLabel = new JLabel("4 x 4");
        gblc.gridx = 2;
        gblc.gridy = 0;
        gridLayout.setConstraints(gridSizeValueLabel, gblc);
        middlePanel.add(gridSizeValueLabel);

        //row 1
        bombLabel = new JLabel("Bombs");
        gblc.gridx = 0;
        gblc.gridy = 1;
        gridLayout.setConstraints(bombLabel, gblc);
        middlePanel.add(bombLabel);

        bombSlider = new JSlider();
        bombSlider.setMinimum(2);
        bombSlider.setMaximum(6);
        bombSlider.setValue(4);
        bombSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                bombValue = source.getValue();
                bombValueLabel.setText("" + bombValue);
            }
        });
        gblc.gridx = 1;
        gblc.gridy = 1;
        gridLayout.setConstraints(bombSlider, gblc);
        middlePanel.add(bombSlider);

        bombValueLabel = new JLabel("4");
        gblc.gridx = 2;
        gblc.gridy = 1;
        gridLayout.setConstraints(bombValueLabel, gblc);
        middlePanel.add(bombValueLabel);


        //*****Bottom Panel
        bottomPanel = new JPanel();
        sc.add(bottomPanel, BorderLayout.SOUTH);

        saveButton = new JButton("Save Settings");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Activate a window closing event on the PopupSetup event
                PopupSetup.this.dispatchEvent(new WindowEvent(
                        PopupSetup.this, WindowEvent.WINDOW_CLOSING
                ));
            }
        });

        newGameButton = new JButton("Start New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset = true;
                //Activate a window closing event on the PopupSetup event
                PopupSetup.this.dispatchEvent(new WindowEvent(

                        PopupSetup.this, WindowEvent.WINDOW_CLOSING
                ));
            }
        });


        bottomPanel.add(saveButton, BorderLayout.EAST);
        bottomPanel.add(newGameButton, BorderLayout.WEST);

        //*****PopupSetup frame properties
        setSize(500,200);
        setResizable(false);//change to false
        setVisible(true);

    } // End of constructor

    public void buttonClicked(String level){
        switch(level.toString()) {
            case "Beginner":
                gridSizeSlider.setValue(4);
                bombSlider.setValue(4);
                break;
            case "Intermediate":
                gridSizeSlider.setValue(8);
                bombSlider.setValue(15);
                break;
            case "Expert":
                gridSizeSlider.setValue(12);
                bombSlider.setValue(40);
                break;
            default:
                System.out.println("Button Set Error - PopupSetup.java");
        }
    }//End buttonClicked

    public int getNumGridSide(){
        return gridSizeValue;
    }// End of getNumGridSide

    public int getNumBombs(){
        return bombValue;
    }//End getNumBombs

    public boolean performResetBoard(){return reset;}
}//End of class
