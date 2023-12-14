package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch; //adicionei uma dependência para a partida (ChessMatch), pois o Rei faz uma associação com a partida de xadrez

	public King(Board board, Color color, ChessMatch chessMatch) {//o terceiro argumento e a referência para a partida
		super(board, color);
		this.chessMatch = chessMatch;//e dessa forma, tanto na declaração da classe, no terceiro argumento como aqui, associa o 'King' com a 'ChessMatch'
	}

	@Override
	public String toString() {
		return "K";
	}

	// método que verifica se o rei pode mover
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor(); //o rei vai se mexer quando a casa estiver vazia ou o lugar da posição estiver uma peça adversária
	}
	//o objetivo desse método é verificar na posição do argumento se existe uma torre, e se essa torre está apta para efetuar o rook
	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0; //se essa peça for diferente de nulo, e se essa peça é uma torre(instanceof), e se a cor dessa torre é igual a cor do meu rei, e se o contador de movimento for igual a zero da torre. 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0); // posição auxiliar

		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw (noroeste - esquerda cima na diagonal)
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne (nordeste - direita cima na diagonal)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw (sudoeste - esquerda baixo na diagonal)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se (sudeste - direita baixo na diagonal)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Movimento especial castling (Roque)
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {//se o contador de movimentos do rei for zero e se a partida não está em check
			//movimento especial castling da torre do lado do rei (torre direita - Roque pequeno)
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3); //vai pegar a posição da torre. Primeiro vai pegar a mesma linha do rei com 'getRow' e após isso vai pegar a coluna mais três casas para a direita, ou seja, a posição onde fica a torre inicialmente
			if(testRookCastling(posT1)) {//existe uma torre nesta posição que está apta para roque
				Position p1 = new Position(position.getRow(), position.getColumn() + 1); //vai pegar a posição da primeira casa a direita do rei
				Position p2 = new Position(position.getRow(), position.getColumn() + 2); //vai pegar a posição da segunda casa a direita do rei
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) { //se as duas casas a direita do rei estiverem vazias, faça...
					mat[position.getRow()][position.getColumn() + 2] = true; //adicionei um movimento possível para o rei, o roque pequeno
				}
			}
			
			//movimento especial castling da torre do lado da rainha (torre esquerda - Roque grande)
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4); //vai pegar a posição da torre. Primeiro vai pegar a mesma linha do rei com 'getRow' e após isso vai pegar a coluna mais quatro casas para a esquerda, ou seja, a posição onde fica a torre inicialmente.
			if(testRookCastling(posT2)) { //existe uma torre nesta posição que está apta para roque
				Position p1 = new Position(position.getRow(), position.getColumn() - 1); //vai pegar a posição da primeira casa a esquerda do rei
				Position p2 = new Position(position.getRow(), position.getColumn() - 2); //vai pegar a posição da segunda casa a esquerda do rei
				Position p3 = new Position(position.getRow(), position.getColumn() - 3); //vai pegar a posição da terceira casa a esquerda do rei
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) { //se as três casas a esquerda do rei estiverem vazias, faça...
					mat[position.getRow()][position.getColumn() - 2] = true; //adicionei mais um movimento possível para o rei, o roque grande
				}
			}
		}

		return mat;
	}

}
