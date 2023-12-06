package application;

import chess.ChessPiece;
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

	/* os métodos são 'static' para não gerar um novo objeto a cada instanciação */

	public static void printBoard(ChessPiece[][] pieces) { // método para imprimir todo o tabuleiro.
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println(); /* após imprimir todas as peças/tabuleiro em uma linha, haverá uma quebra de
								 linha e começara a imprimir a próxima.*/
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) { // método auxiliar para imprimir uma peça.
		if (piece == null) {
			System.out.print("-"); // caso a posição da peça não exista, imprima um "risquinho".
		} else {
			System.out.print(piece); // caso a posição da peça exista, imprima a peça.
		}
		System.out.print(" "); // espaço em branco para que as peças não fiquem grudadas umas nas outras.
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

}
