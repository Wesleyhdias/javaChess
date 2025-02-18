package Aplication;

import java.util.InputMismatchException;
import java.util.stream.Collectors;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.PieceColor;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class UI {
    
    public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc){
        try{
            String str = sc.nextLine();
            char column = str.charAt(0);
            int row = Integer.parseInt(str.substring(1));
            return new ChessPosition(column, row);
    
        }catch(RuntimeException e){
            throw new InputMismatchException("Wrong position, position must be between a1 and h8");
        }
    }

    public static void printPiece(ChessPiece piece){
        if(piece.getColor() == PieceColor.white){
            System.out.print(ANSI_BLUE + piece.toString());
        }else{
            System.out.print(ANSI_YELLOW + piece.toString());
        }
    }

    private static void printCapturedPieces(List<ChessPiece> pieces, PieceColor color){
        List<ChessPiece> capturedPieces = pieces.stream().filter(x-> x.getColor() == color).collect(Collectors.toList());

        System.out.print("CapturedPieces: ");
        if(color == PieceColor.white){
            System.out.print(ANSI_BLUE);
        }else{
            System.out.print(ANSI_YELLOW);
        }
        System.out.println(Arrays.toString(capturedPieces.toArray()));
        System.out.print(ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] pieces){
        System.out.printf("  a b c d e f g h\n");
        for(int i=0; i < pieces.length; i++){
            System.out.printf("%d ", (8-i));
            for(int j=0; j < pieces.length; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        System.out.print(ANSI_WHITE_BACKGROUND); 
                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }else{
                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }
                }else{
                    if(j % 2 == 0){
                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }else{
                        System.out.print(ANSI_WHITE_BACKGROUND);
                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }
                }
            }
            System.out.printf(" %d", (8-i));
            System.out.println();
        }
        System.out.printf("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves){
        System.out.printf("  a b c d e f g h\n");
        for(int i=0; i < pieces.length; i++){
            System.out.printf("%d ", (8-i));
            for(int j=0; j < pieces.length; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        
                        if(possibleMoves[i][j] == true){
                            System.out.print(ANSI_RED_BACKGROUND);
                        }else{
                            System.out.print(ANSI_WHITE_BACKGROUND); 
                        }

                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }else{

                        if(possibleMoves[i][j] == true){
                            System.out.print(ANSI_BLUE_BACKGROUND);
                        }

                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }
                }else{
                    if(j % 2 == 0){

                        if(possibleMoves[i][j] == true){
                            System.out.print(ANSI_BLUE_BACKGROUND);
                        }

                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }else{

                        if(possibleMoves[i][j] == true){
                            System.out.print(ANSI_RED_BACKGROUND);
                        }else{
                            System.out.print(ANSI_WHITE_BACKGROUND); 
                        }

                        if(pieces[i][j] == null){
                            System.out.print("  ");
                        }else{
                            printPiece(pieces[i][j]);
                        }
                        System.out.print(ANSI_RESET);
                    }
                }
            }
            System.out.printf(" %d", (8-i));
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
        System.out.println();
    }


    public static void printMatch(ChessMatch match, List<ChessPiece> captured){
        
        printCapturedPieces(captured, PieceColor.white);
        System.out.println();
        
        printBoard(match.getPieces());
        
        System.out.println("\n");
        printCapturedPieces(captured, PieceColor.black);
        
        System.out.println("\nTurn: " + match.getTurn());
        System.out.println( match.getCurrentPlayer() + " turn.\n");
    }
}
