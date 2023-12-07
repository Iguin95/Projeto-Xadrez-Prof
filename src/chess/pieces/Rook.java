package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color); // vai repassar os dados para o contrutor da superclasse
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above (acima)
		p.setValues(position.getRow() - 1, position.getColumn()); // pego a posição da peça menos um, ou seja, estou
																	// verificando acima da peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // enquanto a posição 'p' existir e não
																				// existir uma peça lá, ou seja,
																				// enquanto a posição estiver vaga, irei
																				// marcar a posição como verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1); // vai fazer a peça andar pra cima
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // se as posições forem livres até encontrar uma
																		// peça adversária, a posição da peça adversária
																		// também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left (esquerda)
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right (direita)
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		// below (baixo)
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) { // existir uma peça lá, ou seja,
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) { // também ficará livre
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
