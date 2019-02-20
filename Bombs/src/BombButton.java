import javax.swing.*;
import java.awt.*;

public class BombButton extends JButton{
    private int neighbors = 0;
    private boolean wasClicked = false;
    private boolean bomb;
    private Icon img; //for bombs
    private int cordX;
    private int cordY;

    public BombButton() {
        super();
        ClassLoader loader = getClass().getClassLoader();
        //img = new ImageIcon(loader.getResource("bombIcon.jpg"));

    } //End constructor

    public void setPosX(int x){cordX = x;}
    public void setPosY(int y){cordY = y;}
    public int getPosX(){return cordX;}
    public int getPosY(){return cordY;}

    public boolean getClicked(){return wasClicked;}

    public int getNeighbors(){return neighbors;}
    public void setNeighbors(int num){
        if(!bomb) {
            neighbors = num;
        } //else neighbors = 0
    }//End setNeighbors

    public void setBomb(boolean bool){ bomb = bool; }
    public boolean isBomb(){ return bomb; }

    public void showBomb(){
        wasClicked = true;
        //if it's a bomb show img
        //if neighbors isn't 0, show
        //if neighbors is 0, flip, but show nothing
        if(bomb){
            super.setText("BOMB");
            super.setBackground(Color.RED);
            //super.setIcon(img);
        }else if(neighbors > 0){
            super.setText("" + neighbors);
            super.setBackground(Color.GRAY);
        }else{
            super.setText("checkmark");
        }

    }//End showBomb

}//End class

