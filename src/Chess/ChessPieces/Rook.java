package Chess.ChessPieces;

import ChessBoard.Position;
import Chess.ChessPiece;
import Chess.PieceColor;
import ChessBoard.Board;

public class Rook extends ChessPiece {

    public Rook(Board board, PieceColor color) {
        super(board, color);
    }

    private boolean hasKing(Position pos) {

        return (getBoard().getPiece(pos).getClass().getName() == "Chess.ChessPieces.King");
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position pos = new Position(0, 0);

        // cima
        pos.setPosition(position.getRow() - 1, position.getColumn());
        while (getBoard().hasPosition(pos)) {

            if (getBoard().hasPiace(pos)) {

                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if (hasKing(pos) && getBoard().hasPosition(pos.getRow() - 1, pos.getColumn())) {
                        mat[pos.getRow() - 1][pos.getColumn()] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() - 1);
        }

        // baixo
        pos.setPosition(position.getRow() + 1, position.getColumn());
        while (getBoard().hasPosition(pos)) {

            if (getBoard().hasPiace(pos)) {
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if (hasKing(pos) && getBoard().hasPosition(pos.getRow() + 1, pos.getColumn())) {
                        mat[pos.getRow() + 1][pos.getColumn()] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() + 1);
        }

        // direita
        pos.setPosition(position.getRow(), position.getColumn() + 1);
        while (getBoard().hasPosition(pos)) {

            if (getBoard().hasPiace(pos)) {
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if (hasKing(pos) && getBoard().hasPosition(pos.getRow(), pos.getColumn() + 1)) {
                        mat[pos.getRow()][pos.getColumn() + 1] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getColumn() + 1);
        }
        if (getBoard().hasPosition(pos)) {

            if (hasOpponentPiece(pos)) {
                mat[pos.getRow()][pos.getColumn()] = true;

            }
        }

        // esquerda
        pos.setPosition(position.getRow(), position.getColumn() - 1);
        while (getBoard().hasPosition(pos)) {

            if (getBoard().hasPiace(pos)) {
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if (hasKing(pos) && getBoard().hasPosition(pos.getRow(), pos.getColumn() - 1)) {
                        mat[pos.getRow()][pos.getColumn() - 1] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getColumn() - 1);
        }

        return mat;
    }

    @Override
    public String toString() {
        return "R ";
    }

}
