package Application;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChessMatch match = new ChessMatch();
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while(true){
            try{
                UI.clearScreen();
                UI.printMatch(match, capturedPieces);
                System.out.print("\nMover peça: ");
                ChessPosition in = UI.readChessPosition(sc);

                boolean[][] moves = match.gamePossibleMoves(in);

                UI.clearScreen();
                UI.printBoard(match.getPieces(), moves);

                System.out.print("\nPara: ");
                ChessPosition out = UI.readChessPosition(sc);
            
                ChessPiece piece = match.movePiece(in, out, sc);

                if(piece != null){
                    capturedPieces.add(piece);
                }

            }catch(ChessException e){ 
                System.out.println(e.getMessage());
                sc.nextLine();

            }catch(InputMismatchException e){ 
                System.out.println(e.getMessage());
                sc.nextLine();
            }  
        }
        // sc.close();
    }
}
