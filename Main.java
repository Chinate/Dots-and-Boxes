/**
 * Created by CHINANATE on 3/29/17.
 */
import java.util.Scanner;
public class Main {

    static int conti = 0;
    static int gamemode = 0;
    static boolean wrong = false;

    static void PrintBoard(GameBoard gb) throws Exception{
        String length = ""+ (gb.columns-1) + (gb.rows-1);
        int diff = length.length()-2;
        System.out.println("");
        System.out.println("");
        if (gamemode==1) System.out.println("Player 1: "+gb.pl4+" Player 2: "+gb.pl5);
        if (gamemode==2) System.out.println("Player: "+gb.pl4+" Computer: "+gb.pl5);
        for (int i = 0; i <(2*gb.rows+1); i++) {
            for (int j = 0; j <(2*gb.columns+1) ; j++) {
                if (((i % 2) ==0)&&(i!=2*gb.rows)) {
                    if (((j % 2) == 0) && (j < 2 * gb.rows)) System.out.print("+");
                    else if (((j % 2) == 0)&&(j== 2* gb.rows)) System.out.println("+");
                    else {
                        if (gb.Lines[i/2][(j-1)/2][0]==0) {System.out.print("    ");
                            for (int k = 0; k <diff ; k++) {
                                System.out.print(" ");
                            }}
                        if (gb.Lines[i/2][(j-1)/2][0]==1) {System.out.print("----");
                            for (int k = 0; k <diff ; k++) {
                                System.out.print("-");
                            }}
                    }
                }
                else if (((i % 2) ==0)&&(i==2*gb.rows)) {
                    if (((j % 2) == 0) && (j < 2 * gb.rows)) System.out.print("+");
                    else if (((j % 2) == 0)&&(j== 2* gb.rows)) System.out.println("+");
                    else {
                        if (gb.Lines[i/2-1][(j-1)/2][3]==0) {System.out.print("    ");
                            for (int k = 0; k <diff ; k++) {
                                System.out.print(" ");
                            }}
                        if (gb.Lines[i/2-1][(j-1)/2][3]==1) {System.out.print("----");
                            for (int k = 0; k <diff ; k++) {
                                System.out.print("-");
                            }}
                    }
                }
                if ((i % 2) ==1) {
                    if (((j % 2)==0)&&(j!=2*gb.rows)) {
                        if (gb.Lines[i/2][j/2][1]==0) System.out.print(" ");
                        if (gb.Lines[i/2][j/2][1]==1) System.out.print("|");
                    }
                    if (((j % 2)==0)&&(j==2*gb.rows)) {
                        if (gb.Lines[i/2][j/2-1][2]==0) System.out.println(" ");
                        if (gb.Lines[i/2][j/2-1][2]==1) System.out.println("|");
                    }
                    if ((j % 2)==1) {
                        if (gb.Board[i/2][j/2]==4) {
                            if (gamemode == 1) {
                                for (int k = 0; k <diff ; k++) {
                                    System.out.print(" ");
                                } System.out.print(" P1 ");}
                            if (gamemode == 2) {for (int k = 0; k <diff ; k++) {
                                System.out.print(" ");
                            }System.out.print(" YO ");}
                        }
                        if (gb.Board[i/2][j/2]==5) {
                            if (gamemode == 1) {
                                for (int k = 0; k <diff ; k++) {
                                    System.out.print(" ");
                                } System.out.print(" P2 ");}
                            if (gamemode == 2) {for (int k = 0; k <diff ; k++) {
                                System.out.print(" ");
                            }System.out.print(" AI ");}
                        }
                        if (gb.Board[i/2][j/2]<4) {
                            String ano = ""+i / 2 + j / 2;
                            String another = ""+ (gb.columns-1) + (gb.rows-1);
                            for (int k = 0; k < another.length()-ano.length(); k++) {
                                System.out.print(" ");
                            }
                            System.out.print(" " + i / 2 + j / 2 + " ");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the size.");
        int rows = -1;
        do {
            System.out.println("Please enter!");
            wrong = false;
            try {
                rows = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Try again");
                wrong = true;
            }
        }while (wrong||rows<1);
        int columns = rows;
        GameBoard gb = new GameBoard(rows, columns);
        System.out.println("Please choose game mode. 1 for local multiplayer, 2 for player versus AI");
        do {
            System.out.println("Please enter!");
            wrong = false;
            try {
                gamemode = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Try again");
                wrong = true;
            }
        }while (wrong||gamemode>3||gamemode<1);
        if (gamemode==1) {
            while (gb.pl4+gb.pl5<gb.NumOfBoxes) {
                ++gb.gameturn;
                if (conti!=5) {
                    if (conti == 4) conti = 0;
                    PrintBoard(gb);
                    System.out.println();
                    System.out.println("Player 1's move!");
                    System.out.println("Please enter row column and side indexes, seperated by space.(0 for top side, 1 left, 2 right, 3 bottom.)");
                    do {
                        wrong = false;
                    try {
                        String move = sc.nextLine();
                        String[] moves = move.split(" ");
                        int trow = Integer.parseInt(moves[0]);
                        int tcol = Integer.parseInt(moves[1]);
                        int tsid = Integer.parseInt(moves[2]);
                        Update ud = new Update(gb, 4, trow, tcol, tsid, true);
                        ud.updater();
                    } catch (Exception e) {
                        System.out.println("Try again");
                        wrong = true;
                    }
                    } while (wrong);
                }
                if (conti!=4) {
                    if (conti == 5) conti = 0;
                    PrintBoard(gb);
                    System.out.println();
                    System.out.println("Player 2's move!");
                    System.out.println("Please enter row column and side indexes, seperated by space.(0 for top side, 1 left, 2 right, 3 bottom.)");
                    do {
                        wrong = false;
                        try {
                            String move = sc.nextLine();
                            String[] moves = move.split(" ");
                            int trow = Integer.parseInt(moves[0]);
                            int tcol = Integer.parseInt(moves[1]);
                            int tsid = Integer.parseInt(moves[2]);
                            Update ud = new Update(gb, 5, trow, tcol, tsid, true);
                            ud.updater();
                        } catch (Exception e) {
                            System.out.println("Try again");
                            wrong = true;
                        }
                    } while (wrong);
                }
            }
            PrintBoard(gb);
            if (gb.pl4>gb.pl5) System.out.println("Player 1 wins!");
            if (gb.pl4<gb.pl5) System.out.println("Player 2 wins!");
            if (gb.pl4==gb.pl5) System.out.println("Tie!");

        }


        if (gamemode==2) {
            while (gb.pl4+gb.pl5<gb.NumOfBoxes) {
                ++gb.gameturn;
                if (conti!=5) {
                    if (conti == 4) conti = 0;
                    PrintBoard(gb);
                    System.out.println();
                    System.out.println("Player's move!");
                    System.out.println("Please enter row column and side indexes, seperated by space.(0 for top side, 1 left, 2 right, 3 bottom.)");
                    do {
                        wrong = false;
                        try {
                            String move = sc.nextLine();
                            String[] moves = move.split(" ");
                            int trow = Integer.parseInt(moves[0]);
                            int tcol = Integer.parseInt(moves[1]);
                            int tsid = Integer.parseInt(moves[2]);
                            Update ud = new Update(gb, 4, trow, tcol, tsid, true);
                            ud.updater();
                        } catch (Exception e) {
                            System.out.println("Try again");
                            wrong = true;
                        }
                    } while (wrong);
                }
                if (conti!=4) {
                    if (conti == 5) conti = 0;
                    AI ai= new AI(gb);
                    int[] aimove = ai.move();
                    Update ud = new Update(gb, 5, aimove[0], aimove[1], aimove[2], true);
                    ud.updater();
                }
            }
            PrintBoard(gb);
            if (gb.pl4>gb.pl5) System.out.println("Player wins!");
            if (gb.pl4<gb.pl5) System.out.println("Computer wins!");
            if (gb.pl4==gb.pl5) System.out.println("Tie!");

        }

        if (gamemode==3) {
            while (gb.pl4+gb.pl5<gb.NumOfBoxes) {
                ++gb.gameturn;
                if (conti != 5) {
                    if (conti == 4) conti = 0;
                    AI ai = new AI(gb);
                    int[] aimove = ai.move();
                    Update ud = new Update(gb, 4, aimove[0], aimove[1], aimove[2], true);
                    ud.updater();
                }
                if (conti != 4) {
                    if (conti == 5) conti = 0;
                    AI ai = new AI(gb);
                    int[] aimove = ai.move();
                    Update ud = new Update(gb, 5, aimove[0], aimove[1], aimove[2], true);
                    ud.updater();
                }
            }
            PrintBoard(gb);
            if (gb.pl4>gb.pl5) System.out.println("Computer 1 wins!");
            if (gb.pl4<gb.pl5) System.out.println("Computer 2 wins!");
            if (gb.pl4==gb.pl5) System.out.println("Tie!");

        }
    }
}
