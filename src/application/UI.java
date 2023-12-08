package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	/* Cores para customizar o terminal */
	// cores das letras
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// cores do background do terminal
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	//código para limpar a tela
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() { 
		System.out.print("\033[H\033[2J"); 
		System.out.flush(); 
	}
	
	
	/* os métodos são 'static' para não gerar um novo objeto a cada instanciação */

	public static ChessPosition readChessPosition(Scanner sc) { //método que vai ler uma posição do usuário
		try {
		String s = sc.nextLine(); //vai ler a entrada do usuário
		char column = s.charAt(0); //vai pegar o primeiro caractere da String, porque a coluna é mencionada pelo primeiro caractere, ex: a1, b6...
		int row = Integer.parseInt(s.substring(1)); //vou recortar meu String a partir da posição 1 e vou converter o resultado para inteiro
		return new ChessPosition(column, row);
		}
		catch(RuntimeException e) {//vai capturar qualquer exceção de RuntimeException
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8."); //exceção que captura erros na entrada de dados pelo usuário
		}
	}
	
	public static void printMatch(ChessMatch chessMatch) {//método que vai imprimir a partida
		printBoard(chessMatch.getPieces()); //vai imprimir o tabuleiro
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer()); //estou aguardado o jogador da cor ... (WHITE/BLACK)
	}
	
	
	public static void printBoard(ChessPiece[][] pieces) { // método para imprimir todo o tabuleiro.
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false); //aqui há o 'false' pois estou indicando para o argumento de background que nenhuma peça inicialmente é pra ter cor de fundo
			}
			System.out.println(); /* após imprimir todas as peças/tabuleiro em uma linha, haverá uma quebra de
								 linha e começara a imprimir a próxima.*/
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) { 
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]); //dependendo da variável do possibleMoves[i][j] eu irei colorir o fundo com as posições possíveis de movimentação
			}
			System.out.println(); 
		}
		System.out.println("  a b c d e f g h");
	}


	private static void printPiece(ChessPiece piece, boolean background) { // método auxiliar para imprimir uma peça.
		if(background == true) { //irá conferir se eu devo ou não colorir o fundo da minha peça
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		//é necessário ter o ANSI_RESET para que a cor somente apareça no lugar desejado e não no tabuleiro todo, ele limpa a cor
		if (piece == null) {
			System.out.print("-" + ANSI_RESET); // caso a posição da peça não exista, imprima um "risquinho".
			
		/*} else {
			System.out.print(piece); // caso a posição da peça exista, imprima a peça.
		}
		*/
			
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" "); // espaço em branco para que as peças não fiquem grudadas umas nas outras.
	}

}
