package application;

import chess.ChessPiece;

public class UI {
	
	/*os métodos são 'static' para não gerar um novo objeto a cada instanciação*/
	
	public static void printBoard(ChessPiece[][] pieces) { //método para imprimir todo o tabuleiro.
		for(int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < pieces.length;j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println(); //após imprimir todas as peças/tabuleiro em uma linha, haverá uma quebra de linha e começara a imprimir a próxima.
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) { //método auxiliar para imprimir uma peça.
		if(piece == null) { //caso a posição da peça não exista, imprima um "risquinho".
			System.out.print("-");
		}
		else {
			System.out.print(piece); //caso a posição da peça exista, imprima a peça.
		}
		System.out.print(" "); //espaço em branco para que as peças não fiquem grudadas umas nas outras.
	}

}
