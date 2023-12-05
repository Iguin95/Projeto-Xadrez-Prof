package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board); //repassa a chamada para o construtor da super classe(class Piece)
		this.color = color;
	}

	public Color getColor() { //Somente o 'get' ´pois não posso deixar a cor de uma peça ser modificada
		return color;
	}


	
	

}