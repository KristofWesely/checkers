import java.util.Scanner;

public class checkers {
    static GameLogic game = new GameLogic();

    public static void main(String[] args) {
        System.out.println("\n\n                ------=+}  Checkers  {+=------       \n\n");
        System.out.println("Input 'test' to run the tests. Any other input will start the game.");
        Scanner reader = new Scanner(System.in);
        String mode = reader.next();
        // Chose between logic test and game start
        System.out.println(mode.toUpperCase());
        if (mode.toUpperCase().equals("TEST")){
            testing();
        }
        else {
            game.start();
        }
    }

    // Test the logic of the game
    public static void testing(){
        System.out.printf("Automated testing started...\n\nThe board is in the initial stage:\n");
        game.board.renderBoard();

        // Create array for results
        boolean results[] = new boolean[30];
        boolean compare[] = new boolean[] {true, false, true, false, true, false, true, true, false, false, false, true, false, true, false, false, false, true, true, false, true, false, false, true, false, true, true, true, false, true};

        // Start test
        results[0] = game.fieldTest("2", "1", 1);                   // Valid
        results[1] = game.fieldTest("rpf,", "1", 2);                // Error
        results[2] = game.fieldTest("0", "0", 0);                   // Valid
        results[3] = game.fieldTest("23", "1", 1);                  // Error
        results[4] = game.fieldTest("7", "6", 2);                   // Valid
        results[5] = game.fieldTest("2.3", "1", 1);                 // Error
        results[6] = game.fieldTest("3", "3", 0);                   // Valid
        results[7] = game.moveTest("2", "5", "3", "4", 2);          // Valid
        results[8] = game.moveTest("0", "5", "      ", "4", 2);     // Error
        results[9] = game.moveTest("4", "5", "3", "4", 1);          // Error
        results[10] = game.moveTest("7", "5", "3", "4", 2);         // Error
        results[11] = game.moveTest("3", "2", "2", "3", 1);         // Valid
        results[12] = game.moveTest("4", "2", "eight", "3", 1);     // Error
        results[13] = game.moveTest("6", "5", "7", "4", 2);         // Valid
        results[14] = game.moveTest("6", "5", "6", "4", 1);         // Error
        results[15] = game.moveTest("-2", "2", "3", "3", 1);        // Error
        results[16] = game.moveTest("2", "1", "3", "0", 1);         // Error
        results[17] = game.moveTest("4", "5", "5", "4", 2);         // Valid
        results[18] = game.moveTest("3", "2", "4", "3", 1);         // Valid
        results[19] = game.moveTest("8", "2", "3", "3", 1);         // Error

        // Modify the board
        System.out.printf("\nMove some pieces: \n");
        game.board.move(new int[]{4,5}, new int[]{5,4});
        game.board.move(new int[]{3,2}, new int[]{4,3});
        game.board.move(new int[]{2,5}, new int[]{1,4});

        // Render the board again to see the changes
        game.board.renderBoard();

        // Continue test
        results[20] = game.moveTest("5", "4", "6", "3", 2);         // Valid
        results[21] = game.moveTest("5", "4", "4", "3", 2);         // Error
        results[22] = game.moveTest("5", "4", "4", "5", 2);         // Error
        results[23] = game.moveTest("1", "2", "0", "3", 1);         // Valid
        results[24] = game.moveTest("4", "3", "3", "2", 1);         // Error
        results[25] = game.moveTest("4", "3", "3", "4", 1);         // Valid
        results[26] = game.moveTest("6", "5", "7", "4", 2);         // Valid
        results[27] = game.moveTest("3", "6", "2", "5", 2);         // Valid
        results[28] = game.moveTest("a3", "e", "7", "7", 2);        // Error
        results[29] = game.moveTest("2", "1", "3", "2", 1);         // Valid

        // Compare results
        for (int i = 0; i<30; i++){
            if (results[i] == compare[i]){
                System.out.println("  > Test"+(i+1)+" passed!");
            }
            else {
			    System.out.println("  X Test"+(i+1)+" NOT passed!");
		    }
        }
    }
}