package chess.ui;

import chess.engine.ChessEngine;

public class Main {
    public static void main(String[] args) {
        ChessEngine chessEngine = new ChessEngine(new ConsolePlayerInput(), new ConsoleChessDisplay());
        chessEngine.run();
    }


}
