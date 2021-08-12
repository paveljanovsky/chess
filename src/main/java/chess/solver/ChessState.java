package chess.solver;

import static chess.solver.Constants.CHESS_BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static chess.solver.ChessPiece.BLACK_BISHOP;
import static chess.solver.ChessPiece.BLACK_KING;
import static chess.solver.ChessPiece.BLACK_KNIGHT;
import static chess.solver.ChessPiece.BLACK_PAWN;
import static chess.solver.ChessPiece.BLACK_QUEEN;
import static chess.solver.ChessPiece.BLACK_ROOK;
import static chess.solver.ChessPiece.WHITE_BISHOP;
import static chess.solver.ChessPiece.WHITE_KING;
import static chess.solver.ChessPiece.WHITE_KNIGHT;
import static chess.solver.ChessPiece.WHITE_PAWN;
import static chess.solver.ChessPiece.WHITE_QUEEN;
import static chess.solver.ChessPiece.WHITE_ROOK;

public final class ChessState {

    final ChessBoard chessBoard;
    final ArrayList<ChessPiece> whiteCapturedPieces;
    final ArrayList<ChessPiece> blackCapturedPieces;

    public static ChessState initialChessState() {
        ChessBoard chessBoard = new ChessBoard();
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            chessBoard.put(new ChessSquare((char) ((int) 'a' + i), 2), WHITE_PAWN);
            chessBoard.put(new ChessSquare((char) ((int) 'a' + i), 7), BLACK_PAWN);
        }

        chessBoard.put(new ChessSquare('a', 1), WHITE_ROOK);
        chessBoard.put(new ChessSquare('h', 1), WHITE_ROOK);
        chessBoard.put(new ChessSquare('a', 8), BLACK_ROOK);
        chessBoard.put(new ChessSquare('h', 8), BLACK_ROOK);

        chessBoard.put(new ChessSquare('b', 1), WHITE_KNIGHT);
        chessBoard.put(new ChessSquare('g', 1), WHITE_KNIGHT);
        chessBoard.put(new ChessSquare('b', 8), BLACK_KNIGHT);
        chessBoard.put(new ChessSquare('g', 8), BLACK_KNIGHT);

        chessBoard.put(new ChessSquare('c', 1), WHITE_BISHOP);
        chessBoard.put(new ChessSquare('f', 1), WHITE_BISHOP);
        chessBoard.put(new ChessSquare('c', 8), BLACK_BISHOP);
        chessBoard.put(new ChessSquare('f', 8), BLACK_BISHOP);

        chessBoard.put(new ChessSquare('d', 1), WHITE_QUEEN);
        chessBoard.put(new ChessSquare('d', 8), BLACK_QUEEN);

        chessBoard.put(new ChessSquare('e', 1), WHITE_KING);
        chessBoard.put(new ChessSquare('e', 8), BLACK_KING);

        return new ChessState(chessBoard, new ArrayList<>(), new ArrayList<>());
    }

    public static ChessState fromStorageRepresentation(String storageRepresentation) {
        return null;
    }

    public static ChessState fromParentChessState(ChessState parentChessState, ChessMove chessMove) {
        ChessBoard chessBoard = new ChessBoard(parentChessState.getChessBoard());
        ArrayList<ChessPiece> whiteCapturedPieces = new ArrayList<>(parentChessState.getWhiteCapturedPieces());
        ArrayList<ChessPiece> blackCapturedPieces = new ArrayList<>(parentChessState.getBlackCapturedPieces());

        Optional<ChessPiece> chessPiece = chessBoard.peek(chessMove.getFromChessSquare());
        if (!chessPiece.isPresent()) {
            throw new InvalidChessMoveException(
                    String.format("Invalid chess move. Trying to move [%s], but found empty square at [%s].",
                            chessMove.getChessPiece(), chessMove.getFromChessSquare()));
        } else if (chessPiece.get() != chessMove.getChessPiece()) {
            throw new InvalidChessMoveException(
                    String.format("Invalid chess move. Trying to move [%s], but found [%s] at [%s].",
                            chessMove.getChessPiece(), chessPiece.get(), chessMove.getFromChessSquare()));
        }
        chessBoard.remove(chessMove.getFromChessSquare());
        Optional<ChessPiece> optionalTargetChessPiece = chessBoard.peek(chessMove.getToChessSquare());
        if (optionalTargetChessPiece.isPresent()) {
            ChessPiece targetChessPiece = optionalTargetChessPiece.get();
            if ((targetChessPiece.isWhite() && chessMove.getChessPiece().isWhite())
                    || (!targetChessPiece.isWhite() && !chessMove.getChessPiece().isWhite())) {
                throw new InvalidChessMoveException(
                        "Invalid chess move, target square contains piece with the same color.");
            }
            if (targetChessPiece.isWhite()) {
                whiteCapturedPieces.add(targetChessPiece);
            } else {
                blackCapturedPieces.add(targetChessPiece);
            }
        }
        chessBoard.put(chessMove.getToChessSquare(), chessMove.getChessPiece());
        return new ChessState(chessBoard, whiteCapturedPieces, blackCapturedPieces);
    }

    public static ChessState fromChessMoves(List<ChessMove> chessMoves) {
        ChessBoard chessBoard = new ChessBoard();
        for (ChessMove chessMove : chessMoves) {
            chessBoard.put(chessMove.getToChessSquare(), chessMove.getChessPiece());
        }
        return new ChessState(chessBoard, new ArrayList<ChessPiece>(), new ArrayList<ChessPiece>());
    }

    public ArrayList<ChessMove> getAvailableWhiteChessMoves() {
        ArrayList<ChessMove> chessMoves = new ArrayList<>();
        for (ChessSquare fromSquare : chessBoard.getWhitePieceLocations()) {
            switch (chessBoard.peek(fromSquare).get()) {
                case WHITE_PAWN:
                    chessMoves.addAll(getPawnMoves(fromSquare, true));
                    break;
                case WHITE_ROOK:
                    chessMoves.addAll(getRookMoves(fromSquare, true));
                    break;
                case WHITE_KNIGHT:
                    chessMoves.addAll(getKnightMoves(fromSquare, true));
                    break;
                case WHITE_BISHOP:
                    chessMoves.addAll(getBishopMoves(fromSquare, true));
                    break;
                case WHITE_QUEEN:
                    chessMoves.addAll(getQueenMoves(fromSquare, true));
                    break;
                case WHITE_KING:
                    chessMoves.addAll(getKingMoves(fromSquare, true));
                    break;
                case BLACK_PAWN:
                case BLACK_ROOK:
                case BLACK_KNIGHT:
                case BLACK_BISHOP:
                case BLACK_QUEEN:
                case BLACK_KING:
                    throw new AssertionError("invalid piece location.");
            }
        }
        return chessMoves;
    }

    public ArrayList<ChessMove> getAvailableBlackChessMoves() {
        ArrayList<ChessMove> chessMoves = new ArrayList<>();
        for (ChessSquare fromSquare : chessBoard.getBlackPieceLocations()) {
            switch (chessBoard.peek(fromSquare).get()) {
                case WHITE_PAWN:
                case WHITE_ROOK:
                case WHITE_KNIGHT:
                case WHITE_BISHOP:
                case WHITE_QUEEN:
                case WHITE_KING:
                    throw new AssertionError("invalid piece location.");
                case BLACK_PAWN:
                    chessMoves.addAll(getPawnMoves(fromSquare, false));
                    break;
                case BLACK_ROOK:
                    chessMoves.addAll(getRookMoves(fromSquare, false));
                    break;
                case BLACK_KNIGHT:
                    chessMoves.addAll(getKnightMoves(fromSquare, false));
                    break;
                case BLACK_BISHOP:
                    chessMoves.addAll(getBishopMoves(fromSquare, false));
                    break;
                case BLACK_QUEEN:
                    chessMoves.addAll(getQueenMoves(fromSquare, false));
                    break;
                case BLACK_KING:
                    chessMoves.addAll(getKingMoves(fromSquare, false));
                    break;
            }
        }
        return chessMoves;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ArrayList<ChessPiece> getWhiteCapturedPieces() {
        return whiteCapturedPieces;
    }

    public ArrayList<ChessPiece> getBlackCapturedPieces() {
        return blackCapturedPieces;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!whiteCapturedPieces.isEmpty()) {
            sb.append("Captured white pieces: ");
        }
        for (ChessPiece piece : whiteCapturedPieces) {
            sb.append(piece.getUnicode() + " ");
        }
        sb.append("\n");

        sb.append(chessBoard.toString());

        if (!blackCapturedPieces.isEmpty()) {
            sb.append("Captured black pieces: ");
        }
        for (ChessPiece piece : blackCapturedPieces) {
            sb.append(piece.getUnicode() + " ");
        }
        sb.append("\n");

        return sb.toString();
    }

    private ArrayList<ChessMove> getPawnMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_PAWN : BLACK_PAWN;
        int rowId = fromSquare.getRowId();
        int columnId = fromSquare.getColumnId();
        ArrayList<ChessMove> chessMoves = new ArrayList<>();
        ChessSquare twoUp = new ChessSquare(isWhite ? 4 : 3, columnId);
        ChessSquare oneUp = new ChessSquare(isWhite ? rowId - 1 : rowId + 1, columnId);
        ChessSquare upLeft = new ChessSquare(isWhite ? rowId - 1 : rowId + 1, isWhite ? columnId - 1 : columnId + 1);
        ChessSquare upRight = new ChessSquare(isWhite ? rowId - 1 : rowId + 1, isWhite ? columnId + 1 : columnId - 1);
        // TODO add En passant capture
        if (((isWhite && fromSquare.getRowId() == 6) || (!isWhite && fromSquare.getRowId() == 1))
        && chessBoard.isAvailable(oneUp)        
        && chessBoard.isAvailable(twoUp)) {
            chessMoves.add(new ChessMove(chessPiece, fromSquare, twoUp));
        }
        if (chessBoard.isAvailable(oneUp)) {
            chessMoves.add(new ChessMove(chessPiece, fromSquare, oneUp));
        }
        if (chessBoard.isTarget(upLeft, isWhite)) {
            chessMoves.add(new ChessMove(chessPiece, fromSquare, upLeft));
        }
        if (chessBoard.isTarget(upRight, isWhite)) {
            chessMoves.add(new ChessMove(chessPiece, fromSquare, upRight));
        }
        return chessMoves;
    }

    private List<ChessMove> getRookMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_ROOK : BLACK_ROOK;
        int rowId = fromSquare.getRowId();
        int columnId = fromSquare.getColumnId();
        ArrayList<ChessSquare> possibleSquares = new ArrayList<>();
        int currentRowId = rowId + 1;
        int currentColumnId = columnId;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId++;
        }
        currentRowId = rowId - 1;
        currentColumnId = columnId;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId--;
        }
        currentRowId = rowId;
        currentColumnId = columnId + 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentColumnId++;
        }
        currentRowId = rowId;
        currentColumnId = columnId - 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentColumnId--;
        }
        return filterPossibleChessSquares(possibleSquares, fromSquare, chessPiece, isWhite);
    }

    private List<ChessMove> getKnightMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_KNIGHT : BLACK_KNIGHT;
        int rowId = fromSquare.getRowId();
        int columnId = fromSquare.getColumnId();
        ArrayList<ChessSquare> possibleSquares = new ArrayList<>(8);
        possibleSquares.add(new ChessSquare(rowId + 2, columnId + 1));
        possibleSquares.add(new ChessSquare(rowId + 2, columnId - 1));
        possibleSquares.add(new ChessSquare(rowId - 2, columnId + 1));
        possibleSquares.add(new ChessSquare(rowId - 2, columnId - 1));
        possibleSquares.add(new ChessSquare(rowId + 1, columnId + 2));
        possibleSquares.add(new ChessSquare(rowId - 1, columnId + 2));
        possibleSquares.add(new ChessSquare(rowId + 1, columnId - 2));
        possibleSquares.add(new ChessSquare(rowId - 1, columnId - 2));
        return filterPossibleChessSquares(possibleSquares, fromSquare, chessPiece, isWhite);
    }

    private List<ChessMove> getBishopMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_BISHOP : BLACK_BISHOP;
        int rowId = fromSquare.getRowId();
        int columnId = fromSquare.getColumnId();
        ArrayList<ChessSquare> possibleSquares = new ArrayList<>();
        int currentRowId = rowId + 1;
        int currentColumnId = columnId + 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId++;
            currentColumnId++;
        }
        currentRowId = rowId + 1;
        currentColumnId = columnId - 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId++;
            currentColumnId--;
        }
        currentRowId = rowId - 1;
        currentColumnId = columnId + 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId--;
            currentColumnId++;
        }
        currentRowId = rowId - 1;
        currentColumnId = columnId - 1;
        while (inBounds(currentRowId, currentColumnId)) {
            ChessSquare toSquare = new ChessSquare(currentRowId, currentColumnId);
            possibleSquares.add(toSquare);
            if (!chessBoard.isAvailable(toSquare)) {
                break;
            }
            currentRowId--;
            currentColumnId--;
        }
        return filterPossibleChessSquares(possibleSquares, fromSquare, chessPiece, isWhite);
    }

    private List<ChessMove> getQueenMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_QUEEN : BLACK_QUEEN;
        ArrayList<ChessMove> chessMoves = new ArrayList<>();
        chessMoves.addAll(getRookMoves(fromSquare, isWhite));
        chessMoves.addAll(getBishopMoves(fromSquare, isWhite));
        return chessMoves.stream()
                .map(move -> new ChessMove(chessPiece, move.getFromChessSquare(), move.getToChessSquare()))
                .collect(Collectors.toList());
    }

    private List<ChessMove> getKingMoves(ChessSquare fromSquare, boolean isWhite) {
        ChessPiece chessPiece = isWhite ? WHITE_KING : BLACK_KING;
        int rowId = fromSquare.getRowId();
        int columnId = fromSquare.getColumnId();
        ArrayList<ChessSquare> possibleSquares = new ArrayList<>(6);
        possibleSquares.add(new ChessSquare(rowId + 1, columnId));
        possibleSquares.add(new ChessSquare(rowId - 1, columnId));
        possibleSquares.add(new ChessSquare(rowId, columnId + 1));
        possibleSquares.add(new ChessSquare(rowId, columnId - 1));
        possibleSquares.add(new ChessSquare(rowId + 1, columnId + 1));
        possibleSquares.add(new ChessSquare(rowId + 1, columnId - 1));
        possibleSquares.add(new ChessSquare(rowId - 1, columnId + 1));
        possibleSquares.add(new ChessSquare(rowId - 1, columnId - 1));
        return filterPossibleChessSquares(possibleSquares, fromSquare, chessPiece, isWhite);
    }

    private List<ChessMove> filterPossibleChessSquares(ArrayList<ChessSquare> possibleSquares, ChessSquare fromSquare,
            ChessPiece chessPiece, boolean isWhite) {
        return possibleSquares.stream().filter(square -> chessBoard.isAvailableOrTarget(square, isWhite))
                .map(square -> new ChessMove(chessPiece, fromSquare, square)).collect(Collectors.toList());
    }

    private static boolean inBounds(int rowId, int columnId) {
        return rowId >= 0 && rowId < CHESS_BOARD_SIZE && columnId >= 0 && columnId < CHESS_BOARD_SIZE;
    }

    private ChessState(ChessBoard chessBoard, ArrayList<ChessPiece> whiteCapturedPieces,
            ArrayList<ChessPiece> blackCapturedPieces) {
        this.chessBoard = chessBoard;
        this.whiteCapturedPieces = whiteCapturedPieces;
        this.blackCapturedPieces = blackCapturedPieces;
    }
}