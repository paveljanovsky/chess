package chess.ui;

import java.util.Random;
import java.util.Scanner;

import chess.solver.ChessMove;
import chess.solver.InvalidChessMoveException;

public class ConsolePlayerInput implements PlayerInput {

    private final Scanner in = new Scanner(System.in);

    @Override
    public ChessMove getMove() {
        while (true) {
            System.out.println("Enter next move:");
            try {
                return new ChessMove(in.nextLine());
            } catch (InvalidChessMoveException e) {
                System.out.println("Invalid chess move");
            }
        }
    }

    @Override
    public boolean getPlayerIsWhite() {
        String input = "";
        while (!("w".equals(input) || "b".equals(input) || "r".equals(input))) {
            System.out.println("Select color: (w)hite, (b)lack, (r)andom:");
            input = in.nextLine();
        }
        if (input == "r") {
            return new Random().nextBoolean();
        }
        return input == "w";
    }
    
}
