package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch(); //instancia a partida de xadrez

		while (true) {
			UI.printBoard(chessMatch.getPieces());//instancia o interface do tabuleiro
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc); //vai ler do usuário a posição de origem

			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc); //vai ler do usuário a posição de destino

			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); //vai mover da origem para o destino
		}

		
	}

}
