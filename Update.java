/**
 * Created by CHINANATE on 3/29/17.
 */
public class Update {
    int row;
    int col;
    int side;
    int pl;
    GameBoard gb;
    boolean contiu;

    Update(GameBoard gb, int pl, int row, int col, int side, boolean contiu) {
        this.gb = gb;
        this.row = row;
        this.col = col;
        this.side = side;
        this.pl = pl;
        this.contiu = contiu;
    }

    void updater() {
        gb.Lines[row][col][side] = 1;
        int[] intu = new int[3];
        intu[0] = -1;
        if ((side==0)&&(row>0)) {intu[0]=row-1; intu[1]=col; intu[2] = 3;}
        if ((side==1)&&(col>0)) {intu[0]=row; intu[1]=col-1; intu[2] = 2;}
        if ((side==2)&&(col<gb.columns-1)) {intu[0]=row; intu[1]=col+1; intu[2] = 1;}
        if ((side==3)&&(row<gb.rows-1)) {intu[0]=row+1; intu[1]=col; intu[2] = 0;}

        for (int i = 0; i <gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) {
                int ct = 0;
                for (int k = 0; k <4 ; k++) {
                    if (gb.Lines[i][j][k]==1) ++ct;
                }
                if ((gb.Board[i][j] < ct)&&(ct==4)) {
                    if (this.contiu) {gb.Board[i][j] = pl; Main.conti = pl;}
                    if (!this.contiu) {gb.Board[i][j] = pl;}
                }
                else if (gb.Board[i][j]<4) gb.Board[i][j]=ct;
            }
        }

        int pl44 = 0;
        int pl55 = 0;
        for (int i = 0; i <gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) {
                if (gb.Board[i][j] == 4) ++pl44;
                if (gb.Board[i][j] == 5) ++pl55;
            }
        }
        gb.pl4 = pl44;
        gb.pl5 = pl55;

        if (intu[0] != -1) this.updater2(intu[0], intu[1], intu[2]);
    }
    void updater2(int row, int col, int side) {
        gb.Lines[row][col][side] = 1;

        for (int i = 0; i <gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) {
                int ct = 0;
                for (int k = 0; k <4 ; k++) {
                    if (gb.Lines[i][j][k]==1) ++ct;
                }
                if ((gb.Board[i][j] < ct)&&(ct==4)) {
                    if (this.contiu) {gb.Board[i][j] = pl; Main.conti = pl;}
                    if (!this.contiu) {gb.Board[i][j] = pl;}}
                else if (gb.Board[i][j]<4) gb.Board[i][j]=ct;
            }
        }

        int pl44 = 0;
        int pl55 = 0;
        for (int i = 0; i <gb.rows ; i++) {
            for (int j = 0; j <gb.columns ; j++) {
                if (gb.Board[i][j] == 4) ++pl44;
                if (gb.Board[i][j] == 5) ++pl55;
            }
        }
        gb.pl4 = pl44;
        gb.pl5 = pl55;

    }
}
