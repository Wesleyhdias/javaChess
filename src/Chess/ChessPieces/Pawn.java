package Chess.ChessPieces;

import Chess.ChessPiece;
import Chess.PieceColor;
import ChessBoard.Board;
import ChessBoard.Position;

public class Pawn extends ChessPiece {

    public Pawn(Board board, PieceColor color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position pos = new Position(0, 0);

        if (color == PieceColor.white) {

            pos.setPosition(position.getRow() - 1, position.getColumn());
            if (!getBoard().hasPiace(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }

            if (position.getRow() == 6) {

                pos.setPosition(pos.getRow() - 1, position.getColumn());

                if (!getBoard().hasPiace(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                }
            }

            // esquerda
            pos.setPosition(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().hasPosition(pos) && hasOpponentPiece(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }

            // direita
            pos.setPosition(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().hasPosition(pos) && hasOpponentPiece(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }

        } else {

            pos.setPosition(position.getRow() + 1, position.getColumn());
            if (!getBoard().hasPiace(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }

            if (position.getRow() == 1) {

                pos.setPosition(pos.getRow() + 1, position.getColumn());

                if (!getBoard().hasPiace(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                }
            }

            // esquerda
            pos.setPosition(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().hasPosition(pos) && hasOpponentPiece(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }

            // direita
            pos.setPosition(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().hasPosition(pos) && hasOpponentPiece(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P ";
    }

}
