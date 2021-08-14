package chess.ui;

import java.util.Random;
import java.util.Scanner;

import chess.solver.ChessMove;
import chess.solver.ChessState;
import chess.solver.InvalidChessMoveException;

public class ConsolePlayerInput implements PlayerInput {

    private final Scanner in = new Scanner(System.in);
    private final PlayerMoveValidator playerMoveValidator = new PlayerMoveValidator();


    @Override
    public ChessMove getValidMove(ChessState currentState) {
        while (true) {
            System.out.println("Enter next move:");
            try {
                return playerMoveValidator.validate(new ChessMove(in.nextLine()), currentState);
            } catch (InvalidChessMoveException e) {
                System.out.println(e.getMessage());
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
