package chess;

import boardgame.Board;
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
	
	//método que vai receber as coordenadas do Xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //vai receber como valor a posição do xadrez e vai converte-la para posição da matriz através da chamada do método 'toPosition()'
	}
	
	private void initialSetup() { //método responsável por iniciar a partida de Xadrez, colocando as peças no tabuleiro
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
