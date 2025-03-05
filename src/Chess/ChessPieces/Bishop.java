package Chess.ChessPieces;

import ChessBoard.Position;
import ChessBoard.Board;
import Chess.PieceColor;
import Chess.ChessPiece;


public class Bishop extends ChessPiece{

    public Bishop(Board board, PieceColor color) {
        super(board, color);
    }

    private boolean hasKing(Position pos){

        return (getBoard().getPiece(pos).getClass().getName() == "Chess.ChessPieces.King");
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position pos = new Position(0, 0);

        // cima direita
        pos.setPosition(position.getRow() - 1, position.getColumn() + 1);
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos)){
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if(hasKing(pos) && getBoard().hasPosition(pos.getRow() - 1, pos.getColumn() + 1)){
                        mat[pos.getRow() - 1][pos.getColumn() + 1] = true;
                    }
                }
                break;             
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setPosition(pos.getRow() - 1, pos.getColumn() + 1);
        }


        // baixo direita
        pos.setPosition(position.getRow() + 1, position.getColumn() + 1);
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos)){
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if(hasKing(pos) && getBoard().hasPosition(pos.getRow() + 1, pos.getColumn() - 1)){
                        mat[pos.getRow() + 1][pos.getColumn() + 1] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setPosition(pos.getRow() + 1, pos.getColumn() + 1);
        }
        

        // cima esquerda
        pos.setPosition(position.getRow() - 1, position.getColumn() - 1);
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos)){
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if(hasKing(pos) && getBoard().hasPosition(pos.getRow(), pos.getColumn() + 1)){
                        mat[pos.getRow() - 1][pos.getColumn() - 1] = true;
                    }
                }
                break;
            }
            
            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setPosition(pos.getRow() - 1, pos.getColumn() - 1);
        }
        if(getBoard().hasPosition(pos)){
    
            if(hasOpponentPiece(pos)){
                mat[pos.getRow()][pos.getColumn()] = true;
                
            }
        }


        // baixo esquerda
        pos.setPosition(position.getRow() + 1, position.getColumn() - 1);
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos)){
                if (hasOpponentPiece(pos)) {
                    mat[pos.getRow()][pos.getColumn()] = true;
                    if(hasKing(pos) && getBoard().hasPosition(pos.getRow(), pos.getColumn() - 1)){
                        mat[pos.getRow() + 1][pos.getColumn() - 1] = true;
                    }
                }
                break;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setPosition(pos.getRow() + 1, pos.getColumn() - 1);
        }
        
        return mat;
    }

    @Override
    public String toString() {
        return "B ";
    }

}
