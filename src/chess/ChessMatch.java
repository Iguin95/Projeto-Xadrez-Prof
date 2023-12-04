package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;//associando uma partida de xadrez a um tabuleiro, pois uma partida de Xadrex tem que ter um tabuleiro(composição)
	
	public ChessMatch() {
		board = new Board(8, 8); //quem tem que saber a dimensão de um tabuleiro de Xadrez, é classe resposável pelas regras do jogo, que no caso é essa. Dimensão do tabuleiro é 8x8.
		initialSetup();//quando se é criada uma nova partida de Xadrez (ChessMatch), se cria um tabuleiro 8x8 e chama o método InitialSetup().
	}
	
	public ChessPiece[][] getPieces(){ //método para retornar uma matriz de peças de Xadrez correspondentes a esta partida.
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //eu estou apenas mostrando a peça de Xadrez (chessPiece) para que meu programa conheça somente a camada de Xadrez, e não a de Tabuleiro, onde contém a peça(piece). Dentro da matriz está a quantidade de linhas e colunas do tabuleiro
		for(int i = 0; i < board.getRows();i++) {
			for(int j = 0;j < board.getColumns();j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);//para cada peça do meu tabuleiro, eu vou fazer um downcasting para 'chesspiece'
			}
		}
		return mat;
	}
	
	private void initialSetup() { //método resposável por iniciar a partida de Xadrez, colocando as peças no tabuleiro
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.WHITE), new Position(0, 4));
	}

}
