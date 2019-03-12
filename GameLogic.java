import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class GameLogic {
    Board board;
    int player;

    // Construct GameLogic
    public GameLogic(){
        board = new Board();
        player = 1;
    }

    // Start the game by making Turns
    public void start(){
        System.out.println("\n\nLet's play some checkers!\n\n");
        while (true) {
            Turn();
        }
    }

    // Make a turn
    private void Turn() {
        int[] cord, cordNew;
        board.renderBoard();
        while (true) {
            //System.out.println("Turn of player no." + Integer.toString(player));
            while (true) {
                System.out.println("Coordinate of piece to move");
                cord = getInput();
                if (board.hasPieceOnField(cord, player)) {
                    break;
                }
                System.out.println("You don't have a piece on that field, try again!");
            }
            while (true) {
                System.out.println("Coordinate of new position");
                cordNew = getInput();
                if (board.hasPieceOnField(cordNew, 0)) {
                    break;
                }
                System.out.println("Field to move is not empty, try again!");
            }
            if (isValidMove(cord, cordNew)){
                board.move(cord, cordNew);
                break;
            }
        }
        player = 3-player;
    }

    // Get input from the user
    private int[] getInput(){ 
        // Using the library initialize a Scanner to read in the user input
        String xIn, yIn;
        int[] cords = new int[2];
        Scanner reader = new Scanner(System.in);
        // Repeat until a valid x coordinate was given
        while(true){
            System.out.printf("Enter X: ");
            xIn = reader.next();
            if (isValidInput(xIn)){
                break; 
            }
        }
        // Repeat until a valid y coordinate was given
        while(true){
            System.out.printf("Enter Y: ");
            yIn = reader.next();
            if (isValidInput(yIn)){
                break;
            }
        }
        cords[0] = Integer.parseInt(xIn);
        cords[1] = Integer.parseInt(yIn);
        return cords;
    }
    
    // Checks whether the move is allowed
    private static boolean isValidInput(String input){
        String[] options = new String[]{"0","1","2","3","4","5","6","7"};
        List menu = Arrays.asList(options);
        if (input.toUpperCase() == "EXIT") {
            System.exit(0);
        }
        if (menu.contains(input)) {
            return true;
        }
        System.out.println("Input one of the following numbers: 0, 1, 2, 3, 4, 5, 6, 7 or 'exit' to quit");
        return false;
    }

    // Checks whether the move is allowed
    private boolean isValidMove(int[] cord, int[] cordNew){
        // Define the forward direction for each player
        int direction;
        if (player == 1) {
            direction = 1;
        }
        else {
            direction = -1;
        }
        // Check wheter the move was forward diagonal, if it was replace the labels in the matrix accordingly
        if ((cordNew[1]-cord[1])==direction && Math.abs(cordNew[0]-cord[0])==1){
            // If the move was valid,
            return true;
        }
        System.out.println("You cannot move like that. Try again or input 'exit' to quit.");
        return false;
    }

    // Test values of specified fields
    public boolean fieldTest(String x, String y, int fieldValue){
        System.out.println("  -- fieldTest: (" + x +", " + y + ")");
        System.out.println("Inputcheck and test wheter the value of the field (" + x +", " + y + ") equals "+player);
        if (isValidInput(x) && isValidInput(y)) {
            System.out.println("The inputs are valid");
            int[] cords = new int[2];
            cords[0] = Integer.parseInt(x);
            cords[1] = Integer.parseInt(y);
            if (board.hasPieceOnField(cords, fieldValue)){
                System.out.println("Field value matches the player value");
                return true;
            }
        }
        return false;
    }

    // Test validity of possible moves
    public boolean moveTest(String x0, String y0, String x1, String y1, int fieldValue){
        int[] cord = new int[2];
        int[] cordNew = new int[2];
        System.out.println("\n----- moveTest from (" + x0 +", " + y0 + ") to (" + x1 +", " + y1 + ") by player " + player);
        System.out.println("First check the fields using fieldTest:");
        if (fieldTest(x0, y0, fieldValue) && fieldTest(x1, y1, 0)){
            System.out.println("Given field values are valid for a move, let's check wheter the move is valid");
            cord[0] = Integer.parseInt(x0);
            cord[1] = Integer.parseInt(y0);
            cordNew[0] = Integer.parseInt(x1);
            cordNew[1] = Integer.parseInt(y1);
            player = fieldValue;
            if (isValidMove(cord, cordNew)){
                System.out.println("The move is valid");
                return true;
            }
        }
        return false;
    }
}