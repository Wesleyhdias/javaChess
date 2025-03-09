package Chess;

// import java.util.stream.Collectors;

import Chess.ChessPieces.King;
import Chess.ChessPieces.Pawn;
import Chess.ChessPieces.Rook;
import Chess.ChessPieces.Queen;
import Chess.ChessPieces.Bishop;
import Chess.ChessPieces.Horse;

// import java.util.ArrayList;
import java.util.List;

import ChessBoard.Position;
import ChessBoard.Board;
import ChessBoard.Piece;

public class ChessMatch {

    private PieceColor currentPlayer;
    private boolean inCheck;
    private Board board;
    private int turn;

    protected Position oppCheckingPiece = null;

    public ChessMatch() {
        board = new Board(8, 8);
        currentPlayer = PieceColor.white;
        inCheck = false;
        turn = 1;

        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PieceColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    private PieceColor getOppColor(PieceColor color) {
        return (color == PieceColor.white) ? PieceColor.black : PieceColor.white;
    }

    private void nextTurn() {
        turn++;

        currentPlayer = (currentPlayer == PieceColor.white) ? PieceColor.black : PieceColor.white;

        inCheck = isInCheck();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }

        return mat;
    }

    public void placeNewPiece(char colunm, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
    }

    private ChessPiece getKing(PieceColor color) {

        for (ChessPiece piece : board.getOnBoardColoredPieces(color)) {
            if (piece.getClass().getName() == "Chess.ChessPieces.King") {
                return piece;
            }
        }

        throw new ChessException("como que não tem rei na partida????");

    }

    private boolean isInCheck() {
        Position king = getKing(currentPlayer).getChessPosition().toPosition();
        List<ChessPiece> oppPieces = board.getOnBoardColoredPieces(getOppColor(currentPlayer));

        for (ChessPiece piece : oppPieces) {
            boolean[][] pMoves = piece.possibleMoves();

            if (pMoves[king.getRow()][king.getColumn()]) {
                oppCheckingPiece = piece.getChessPosition().toPosition();
                return true;
            }
        }
        return false;

    }

    private void ableToMove(Position position) {
        if (!board.hasPiace(position)) {
            throw new ChessException("there is no piece to be moved in informed position");
        }
        if (currentPlayer != ((ChessPiece) board.getPiece(position)).getColor()) {
            throw new ChessException("you can move only your pieces.");
        }
        if (!board.getPiece(position).hasPossibleMove()) {
            throw new ChessException("there is no move available for the informed piece");
        }
    }

    private void ableToMoveTo(Position inPosition, Position outPosition) {
        if (!board.getPiece(inPosition).possibleMove(outPosition)) {
            throw new ChessException("this piece cant move to informed position");
        }
    }

    private ChessPiece move(Position inPosition, Position outPosition) {
        Piece p = board.removePiece(inPosition);
        Piece capturedPiece = board.removePiece(outPosition);
        board.placePiece(p, outPosition);

        return (ChessPiece) capturedPiece;
    }

    private void undoMove(Position inPosition, Position outPosition, ChessPiece capturedPiece) {

        Piece p = board.removePiece(outPosition);
        board.placePiece(p, inPosition);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, outPosition);
        }

    }

    public ChessPiece movePiece(ChessPosition inChessPosition, ChessPosition outChessPosition) {

        Position inPosition = inChessPosition.toPosition();
        Position outPosition = outChessPosition.toPosition();

        ableToMove(inPosition);
        ableToMoveTo(inPosition, outPosition);

        ChessPiece capturedPiece = move(inPosition, outPosition);

        if (isInCheck()) {
            undoMove(inPosition, outPosition, capturedPiece);
            if (inCheck)
                throw new ChessException("King is in check! you must protect him!");

            throw new ChessException("You cant left the King in check!");
        }

        nextTurn();

        return capturedPiece;

    }

    public boolean[][] gamePossibleMoves(ChessPosition inChessPosition) {
        Position inPosition = inChessPosition.toPosition();
        ableToMove(inPosition);

        return board.getPiece(inPosition).possibleMoves();
    }

    private void initialSetup() {
        // peças brancas
        placeNewPiece('a', 1, new Rook(board, PieceColor.white));
        placeNewPiece('b', 1, new Horse(board, PieceColor.white));
        placeNewPiece('c', 1, new Bishop(board, PieceColor.white));
        placeNewPiece('d', 1, new Queen(board, PieceColor.white));
        placeNewPiece('e', 1, new King(board, PieceColor.white));
        placeNewPiece('f', 1, new Bishop(board, PieceColor.white));
        placeNewPiece('g', 1, new Horse(board, PieceColor.white));
        placeNewPiece('h', 1, new Rook(board, PieceColor.white));

        placeNewPiece('h', 2, new Pawn(board, PieceColor.white));
        placeNewPiece('b', 2, new Pawn(board, PieceColor.white));
        placeNewPiece('a', 2, new Pawn(board, PieceColor.white));

        // peças pretas
        placeNewPiece('a', 8, new Rook(board, PieceColor.black));
        placeNewPiece('b', 8, new Horse(board, PieceColor.black));
        placeNewPiece('c', 8, new Bishop(board, PieceColor.black));
        placeNewPiece('d', 8, new Queen(board, PieceColor.black));
        placeNewPiece('e', 8, new King(board, PieceColor.black));
        placeNewPiece('f', 8, new Bishop(board, PieceColor.black));
        placeNewPiece('g', 8, new Horse(board, PieceColor.black));
        placeNewPiece('h', 8, new Rook(board, PieceColor.black));

        placeNewPiece('h', 7, new Pawn(board, PieceColor.black));
        placeNewPiece('b', 7, new Pawn(board, PieceColor.black));
        placeNewPiece('a', 7, new Pawn(board, PieceColor.black));
    }

}
