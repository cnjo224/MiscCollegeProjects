import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class Board {
    private ActionListener AL;
    private BombButton [][] mineButtons = new BombButton[12][12];
    private int arrSize = 12;

    public Board(int size, int numBombs, ActionListener action){
        AL = action;
        initBoard(size, numBombs,false);
    } //End constructor

    public void initBoard(int size, int numBombs, boolean eraseBoard){
        //if board was already set up
        if(eraseBoard && size < arrSize){
            //set to null all the elements in the mineButtons array that won't be used
            for(int i = size-1; i < arrSize; i++){
                for(int j = size-1; j < arrSize; j++){
                    mineButtons[i][j] = null;
                }
            }//close for loop
        }// close if statement

        //perform constructor activities
        arrSize = size;
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize; j++){
                //create the button, either with or without a bomb
                //add the action listener

                BombButton btn = new BombButton();
                btn.addActionListener(AL);

                if(numBombs > 0){ //the first numBomb amount will be created with bombs, the rest without
                    btn.setBomb(true);
                    numBombs--;
                }else{
                    btn.setBomb(false);
                }

                //Fill the mineButtons array
                mineButtons[i][j] = btn;
            }
        }//for loop

        //Shuffle all the buttons
        shuffleBoard();

        //modify the button to count the mines around it
        findNeighbors();
    }

    public void findNeighbors(){

        //iterate through the board
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize; j++){
                int counter = 0;
                //iterate through the 8 possible cells, including the current one
                for(int k = -1; k < 2; k++){
                    for(int n = -1; n < 2; n++){
                        //if the neighbor exists, check if it's a bomb and increment if true
                        if(withinBounds(i+k,j+n) && mineButtons[i+k][j+n].isBomb()){
                            counter++;
                        }
                    }
                }//End 8 possibilities

                //set the neighbor bomb count for each cell, ignore if cell is bomb
                mineButtons[i][j].setNeighbors(counter);

            }
        }//End iterate board

    }//End findNeighbors

    public boolean withinBounds(int i, int j){
        if(i >= 0 && i < arrSize
                && j >= 0 && j < arrSize) {
            return true;
        }
        return false;
    }//End withinBounds : helper for findNeighbors

    public void performClearing(int i, int j){
        /*stop recursion if current button:
        *   is out of bounds of the board
        *   isn't a value of 0
        *   or has already been clicked once
        * */

        if(!withinBounds(i, j) || mineButtons[i][j].getNeighbors() > 0 || mineButtons[i][j].getClicked()){
            return;
        }

        //if the button has 0 neighbors, recursively call performClearing
        if(mineButtons[i][j].getNeighbors() == 0){
            //show the current button
            mineButtons[i][j].showBomb();

            //Check 4 directions only, do not do diagonals
            performClearing(i -1, j);
            performClearing(i + 1, j);
            performClearing(i, j-1);
            performClearing(i, j+1);

        }
    } // End performClearing

    public void fillBoard(JPanel view){
        //fill the board with buttons
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize; j++){
                view.add(mineButtons[i][j]);
            }
        } //for loop
    } //End fillBoard

    public void shuffleBoard(){
        //Use Fisher-Yates algorithm with respect to 2d arrays
        Random random = new Random();
        for(int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize; j++){
                int rand1 = random.nextInt(i+1);
                int rand2 = random.nextInt(j+1);

                BombButton tempBtn = mineButtons[i][j];
                mineButtons[i][j] = mineButtons[rand1][rand2];
                mineButtons[rand1][rand2] = tempBtn;
            }
        }

        //set the x and y coordinates of each button
        for(int y = 0; y < arrSize; y++){
            for(int x = 0; x < arrSize; x++){
                mineButtons[y][x].setPosX(x);
                mineButtons[y][x].setPosY(y);
            }
        }

    }

    public void stopGame(){
        //show all the items on the board
        for (int i = 0; i < arrSize; i++){
            for(int j = 0; j < arrSize; j++){
                if(!mineButtons[i][j].getClicked()){
                    mineButtons[i][j].showBomb();
                }
            }
        }
    }//End stopGame


}//End class
