package chess.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import chess.solver.ChessState;

@SpringBootApplication
public class Main {
    public static void main(String[] args)
    {
        ChessState chessState = ChessState.initialChessState();
        chessState.print();
    }
}
