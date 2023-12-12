package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color); // vai repassar os dados para o contrutor da superclasse
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// nw (noroeste)
		p.setValues(position.getRow() - 1, position.getColumn() - 1); // pego a posição da peça menos um, ou seja, estou
																	// verificando acima da peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição 'p' existir e não
																				// existir uma peça lá, ou seja,
																				// enquanto a posição estiver vaga, irei
																				// marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1); // vai verificar a diagonal
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se as posições forem livres até encontrar uma
																		// peça adversária, a posição da peça adversária
																		// também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne (nordeste)
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1,p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se (sudeste)
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1,p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw (sudoeste)
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1,p.getRow() -1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
