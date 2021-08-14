package chess.ui;

import chess.solver.ChessState;

public class ConsoleChessDisplay implements ChessDisplay {

    @Override
    public void displayChessState(ChessState currentState) {
        System.out.println(currentState);
    }

    @Override
    public void announceNextMove(boolean isWhite, boolean isPlayer) {
        System.out.println(String.format("Next move by %s %s player.", 
          isWhite ? "white" : "black", 
          isPlayer ? "human" : "AI"));
    }
    
}
