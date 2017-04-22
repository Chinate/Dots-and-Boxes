import java.io.Serializable;


/**
 * Created by CHINANATE on 3/29/17.
 */
public class GameBoard implements Serializable{
    protected int rows;
    protected int columns;
    protected int NumOfBoxes;
    protected int[][] Board;
    protected int[][][] Lines;
    public int gameturn = 0;
    public int pl4 = 0;
    public int pl5 = 0;

    GameBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.NumOfBoxes = rows*columns;

        this.Board = new int[rows][columns];
        for (int i = 0; i <this.Board.length ; i++) {
            for (int j = 0; j <this.Board[0].length ; j++) {
                this.Board[i][j] = 0;
            }
        }
        //Initialize the game board as a 2D array, each array element represents a box on the game board.
        //Its value represents the number of lines surrounding it.
        //4 represents the player owns the box and 5 represents computer AI owns the box.

        this.Lines = new int[rows][columns][4];
        for (int i = 0; i <this.Lines.length ; i++) {
            for (int j = 0; j <this.Lines[0].length ; j++) {
                for (int k = 0; k <4 ; k++) {
                    this.Lines[i][j][k] = 0;
                }
            }
        }
        //Representing all the lines on the board as a 3D array. 0 indicates the line is not drawn and 1 indicates the line is drawn.
        //First two indexes represent the box and the third index represent a line surround the box.
        //k=0 the top line, k=1 the left, k=2 the right and k=3 the bottom line.
    }
}
