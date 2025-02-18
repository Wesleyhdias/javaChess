package Chess;

import ChessBoard.Position;
import ChessBoard.Piece;
import ChessBoard.Board;

public abstract class ChessPiece extends Piece{

    private PieceColor color;

    public ChessPiece(Board board, PieceColor color) {
        super(board);
        this.color = color; 
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceColor getOppColor(PieceColor color) {
        return (color == PieceColor.white) ? PieceColor.black : PieceColor.white;
    }

    public ChessPosition getChessPosition() {
        return new ChessPosition((char)(position.getColumn() + 'a'),  (8 - position.getRow()));
    }

    protected boolean hasOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().getPiece(position);

        return piece.getColor() != color && piece != null;
    }

}
