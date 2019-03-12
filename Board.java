public class Board {
    int [][] board;

    // By default initialize the traditional starter board setup
    public Board() {
        int[][] init = {
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {2,0,2,0,2,0,2,0},
            {0,2,0,2,0,2,0,2},
            {2,0,2,0,2,0,2,0}
        };
        board = init;
    }

    // It is possible to initialize the board with custom setup
    public Board(int[][] mat) {
        board = mat;
    }

    // Move a piece
    public void move(int[] cord, int[] cordNew) {
        board[cordNew[1]][cordNew[0]] = board[cord[1]][cord[0]];
        board[cord[1]][cord[0]] = 0;
    }

    // Check wheter the field given by the cordinates has the given value
    public boolean hasPieceOnField(int[] cords, int player) {
        if (board[cords[1]][cords[0]] == player) {
            return true;
        }
        return false;
    }

    // Render board
    public void renderBoard() {
        for (int i = 0; i<10; i++) {
            if (i == 0) {
                System.out.println("   0 1 2 3 4 5 6 7    <- X axis");
                System.out.println("  +----------------+");
            }
            else if (i != 9) {
                String line = Integer.toString(i-1) + " |";
                for (int j = 0; j<8; j++){
                    if (board[i-1][j] == 0){
                        line = line + "  ";
                    }
                    else {
                        line = line+Integer.toString(board[i-1][j])+" ";
                    }
                }
                System.out.println(line + "|");
            }
            else{
                System.out.println("  +----------------+");
                System.out.println("   0 1 2 3 4 5 6 7\n");
            }
        }
    }
}