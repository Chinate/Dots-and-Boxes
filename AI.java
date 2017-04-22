/**
 * Created by CHINANATE on 3/30/17.
 */
import org.apache.commons.lang3.SerializationUtils;
//`
import java.util.ArrayList;
import java.util.List;


public class AI {
    private GameBoard gb;
    private GameBoard vgb;
    List<int[]> possible;
    int[] emptyfortest = {0,0,0};

    AI(GameBoard gb) {
        this.gb = gb;

        possible = new ArrayList<int[]>();
    }

    int[] move() {

        for (int i = 0; i <gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) { //Iterate through every box

                if (gb.Board[i][j]==3) {for (int k = 0; k <4 ; k++) {
                    if (gb.Lines[i][j][k]==0) {int[] posi = {i,j,k}; return posi;}
                }}

                if ((gb.Board[i][j]==0)||(gb.Board[i][j]==1)) { //Box should not be surrounded by 2 lines
                    for (int k = 0; k <4 ; k++) { //Iterate through every side
                        if (gb.Lines[i][j][k]==0) { //Line is not drawn
                            if (k==0&&i==0) {int[] posi = {i,j,k}; possible.add(posi);}
                            else if ((k==0)&&(gb.Board[i-1][j]<2)) {int[] posi = {i,j,k}; possible.add(posi);}

                            if (k==1&&j==0) {int[] posi = {i,j,k}; possible.add(posi);}
                            else if ((k==1)&&(gb.Board[i][j-1]<2)){int[] posi = {i,j,k}; possible.add(posi);}

                            if (k==2&&j==gb.columns-1){int[] posi = {i,j,k}; possible.add(posi);}
                            else if ((k==2)&&(gb.Board[i][j+1]<2)) {int[] posi = {i,j,k}; possible.add(posi);}

                            if (k==3&&i==gb.rows-1) {int[] posi = {i,j,k}; possible.add(posi);}
                            else if ((k==3)&&(gb.Board[i+1][j]<2)) {int[] posi = {i,j,k}; possible.add(posi);}

                        }
                    }
                }
            }
        }
        if (possible.size()==0) {
            try{this.movestate2();} catch (Exception cnse){}}
        return (possible.get((int)(possible.size()*Math.random())));
    }

    void movestate2() throws Exception{
        int puhaha = 0;
        List<ArrayList<int[]>> chains = new ArrayList<>();
        int[][] alchain = new int[gb.rows][gb.columns];
        for (int i = 0; i < gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) { //Iterate through every box
                if ((gb.Board[i][j]==2)&&(alchain[i][j]==0)) { //undocumented chain box
                    this.vgb = SerializationUtils.clone(gb);
                    int k2 = 0;
                    for (int k = 0; k < 4; k++) {
                        if (vgb.Lines[i][j][k] == 0) {
                            k2 = k;
                        }
                    }
                    int initial = vgb.pl5;
                    int initial2 = vgb.pl5;
                    int[][] iniboa = new int[vgb.rows][vgb.columns];
                    for (int l = 0; l < vgb.rows; l++) {
                        for (int m = 0; m < vgb.columns; m++) {
                            iniboa[l][m] = vgb.Board[l][m];
                        }
                    }
                    Update ud = new Update(vgb, 5, i, j, k2, false);
                    ud.updater();
                    while (vgb.pl5 == initial2) {
                        for (int l = 0; l < vgb.rows; l++) {
                            for (int m = 0; m < vgb.columns; m++) { //Iterate through every box
                                if (vgb.Board[l][m] == 3) {
                                    for (int n = 0; n < 4; n++) {
                                        if (vgb.Lines[l][m][n] == 0) {
                                            Update vud = new Update(vgb, 5, l, m, n, false);
                                            vud.updater();
                                            alchain[l][m] = 1;
                                            ++initial2;
                                        }
                                    }
                                    boolean done = true;
                                    for (int k = 0; k <vgb.rows ; k++) {
                                        for (int n = 0; n <vgb.columns ; n++) {
                                            if (vgb.Board[k][n] == 3) done = false;
                                        }
                                    }
                                    if (done) initial2+=2;
                                }
                            }
                        }
                    }
                    int chainum = vgb.pl5 - initial;
                    ArrayList<int[]> boxes = new ArrayList<>();
                    int[] inteme = {-1, chainum};
                    boxes.add(inteme);
                    for (int l = 0; l < vgb.rows; l++) {
                        for (int m = 0; m < vgb.columns; m++) {
                            if ((vgb.Board[l][m] - iniboa[l][m]) == 3) {
                                int[] inteme2 = {l,m};
                                boxes.add(inteme2);
                                alchain[l][m] = 1;
                            }
                        }
                    }
                    chains.add(boxes);
                }
            }
        }

        Main.PrintBoard(gb);
        System.out.println("There are "+chains.size()+" chains in total.");
        for (int i = 0; i <chains.size() ; i++) {
            System.out.println("Chain "+ (i+1) +" has "+ chains.get(i).get(0)[1] +" boxes, starting at box "+ chains.get(i).get(1)[0] +" "+chains.get(i).get(1)[1]);
        }
        System.exit(1);

        //for (int i = 0; i <chains.size() ; i++) {
        //    if (((chains.get(i)).get(0))==1)
        //}

    }
}
