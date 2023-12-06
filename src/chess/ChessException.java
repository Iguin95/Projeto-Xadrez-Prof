package chess;

import boardgame.BoardException;

//toda ChessException também é uma BoardException, pois toda exceção de xadrez também configura uma exceção de tabuleiro. Então, toda captura de uma exceção de xadrez também posso capturar uma exceção de tabuleiro, dessa forma fica mais simples o compilador efetuar as operações de exceções

public class ChessException extends BoardException { //Exceção da camada Chess (Organização em camadas)
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	}
}
