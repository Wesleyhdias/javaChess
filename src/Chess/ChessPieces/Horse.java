package Chess.ChessPieces;

import Chess.ChessPiece;
import Chess.PieceColor;
import ChessBoard.Board;
import ChessBoard.Position;

public class Horse extends ChessPiece{
    public Horse(Board board, PieceColor color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position pos = new Position(0, 0);

        // duas cadas para cima
        pos.setPosition(position.getRow() - 2, position.getColumn());

        // cima direita
        pos.setColumn(pos.getColumn() + 1);
        
        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // cima esquerda
        pos.setColumn(pos.getColumn() - 2);

        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // duas casas para baixo
        pos.setPosition(position.getRow() + 2, position.getColumn());

        // baixo direita
        pos.setColumn(pos.getColumn() + 1);
        
        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // baixo esquerda
        pos.setColumn(pos.getColumn() - 2);

        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // duas casas para esquerda
        pos.setPosition(position.getRow(), position.getColumn() - 2);

        // esquerda cima
        pos.setRow(pos.getRow() - 1);
        
        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // esquerda baixo
        pos.setRow(pos.getRow() + 2);

        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        
        // duas casas para direita
        pos.setPosition(position.getRow(), position.getColumn() + 2);

        // direita cima
        pos.setRow(pos.getRow() + 1);
        
        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){
                System.out.println("deu tru aqui!!");
                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }

        // direita baixo
        pos.setRow(pos.getRow() - 2);

        if(getBoard().hasPosition(pos)){
            if(!getBoard().hasPiace(pos) || hasOpponentPiece(pos)){

                mat[pos.getRow()][pos.getColumn()] = true;
            }
        }
        
        return mat;
    }

    @Override
    public String toString() {
        return "H ";
    }

}
