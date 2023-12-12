package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch(); //instancia a partida de xadrez
		List<ChessPiece> captured = new ArrayList<>();

		
		while (!chessMatch.getCheckMate()) { //enquanto minha partida não estiver em xeque-mate, faça...
			try {
				UI.clearScreen(); //a cada vez que rodar o loop, a tela será limpa
				UI.printMatch(chessMatch, captured);//vai chamar a partida agora
				//UI.printBoard(chessMatch.getPieces());//instancia o interface do tabuleiro
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc); //vai ler do usuário a posição de origem
	
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves); //sobrecarga do método 'printBoard' que irá imprimir os movimentos possíveis
				
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc); //vai ler do usuário a posição de destino
	
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target); //vai mover da origem para o destino
				//Sempre que eu realizar um movimento de peça (linha acima) e me retornar uma peça capturada, eu passarei essa peça pra lista de peças capturadas (if abaixo)
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		UI.clearScreen();
		UI.printMatch(chessMatch, captured); //quando o While falhar, ou seja, quando houver um xeque-mate, vai imprimir a partida do jeito que está no final
	
	}

}
