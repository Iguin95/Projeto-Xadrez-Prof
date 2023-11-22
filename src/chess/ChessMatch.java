package chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;//associando uma partida de xadrez a um tabuleiro, pois uma partida de Xadrex tem que ter um tabuleiro(composição)
	
	public ChessMatch() {
		board = new Board(8, 8); //quem tem que saber a dimensão de um tabuleiro de Xadrez, é classe resposável pelas regras do jogo, que no caso é essa. Dimensão do tabuleiro é 8x8.
	}
	
	public ChessPiece[][] getPieces(){ //método para retornar uma matriz de peças de Xadrez correspondentes a esta partida.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //eu estou apenas mostrando a peça de Xadrez (chessPiece) para que meu programa conheça somente a camada de Xadrez, e não a de Tabuleiro, onde contém a peça(piece). Dentro da matriz está a quantidade de linhas e colunas do tabuleiro
		for(int i = 0; i < board.getRows();i++) {
			for(int j = 0;j < board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

}
