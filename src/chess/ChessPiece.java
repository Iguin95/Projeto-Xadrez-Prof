package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board); //repassa a chamada para o construtor da super classe(class Piece)
		this.color = color;
	}
	

	public Color getColor() { //Somente o 'get' ´pois não posso deixar a cor de uma peça ser modificada
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() { //o meu programa não pode ter acesso a posição de matriz da peça já que também ela está definida com protected na Classe 'Piece'. O programa terá acesso a posição de xadrez através deste método 
		return ChessPosition.fromPosition(position);
	}

	//a próxima operação vai ficar nesta classe abstrata pois ela seráe reaproveitada para todas as outras peças
	
	//método que verifica se existe uma peça adversária em da posição
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color; //vai testar se existe uma peça na posição e se a peça tem a cor diferente  
	}

}
