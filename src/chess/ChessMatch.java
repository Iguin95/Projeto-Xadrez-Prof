package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;//associando uma partida de xadrez a um tabuleiro, pois uma partida de Xadrex tem que ter um tabuleiro(composição)
	
	public ChessMatch() {
		board = new Board(8, 8); //quem tem que saber a dimensão de um tabuleiro de Xadrez, é classe resposável pelas regras do jogo, que no caso é essa. Dimensão do tabuleiro é 8x8.
		turn = 1; //o turno no início da partida vale um.
		currentPlayer = Color.WHITE; //a peça que começa no xadrez é a peça branca, então no primeiro turno será uma peça branca a mover primeiro
		initialSetup();//quando se é criada uma nova partida de Xadrez (ChessMatch), se cria um tabuleiro 8x8 e chama o método InitialSetup().
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
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
	//esse método serve para que no programa principal, eu possa imprimir as posições possíveis a partir de uma posição de origem
	public boolean[][] possibleMoves(ChessPosition sourcePosition){ //operação de movimentos possíveis que retorna um booleano com as posições liberadas para que a minha aplicação possa colorir o fundo das posições possíveis de movimentação
		Position position = sourcePosition.toPosition(); //converte a posição de xadrez para uma posição normal
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();// vou retornar as posições possíveis
	}
	
	//método que moverá uma peça e se for o caso, capturará a peça inimiga
	                                              /*posição de origem*/         /*posição de destino*/
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition(); //vai converter para posição de matriz a posição original e a de destino
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);//operação responsável por verificar se existe a posição de origem, caso não exista, lançará uma excessão
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);//
		nextTurn();//esse método é chamado aqui pois se troca o turno após uma jogada
		return (ChessPiece) capturedPiece; //o DownCasting foi feito pois a capturedPiece era do tipo Piece 
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //retirei a peça da sua posição de origem
		Piece capturedPiece = board.removePiece(target); //eu vou remover a possível peça que está na posição de destino
		board.placePiece(p, target);//colocará a peça a ser movida na posição de destino
		return capturedPiece;
		
	}
	
	private void validateSourcePosition(Position position){//validará se na posição de origem existe uma peça
		if(!board.thereIsAPiece(position)) {//se não existir uma peça nessa posição eu lançarei uma exceção
			throw new ChessException("There is no piece on source position."); //não existe peça na posição de origem//
		}
		/*exceção para caso o jogador esteja tentando mover uma peça adversária*/
		if(currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {//eu pego a peça do tabuleiro na certa posição, faço o downcasting para ChessPiece pois o getColor é uma propriedade da classe, e o a classe Piece é uma classe mais genérica
			throw new ChessException("The chosen piece is not yours"); //a peça escolhida não é sua//
		}
		if(!board.piece(position).isThereAnyPossibleMove()) { //se não tiver nenhum movimento possível
			throw new ChessException("There is no possible moves for the chosen piece"); //não existe movimento possíveis para a peça escolhida//
		}
	}
	
	private void validateTargetPosition(Position source,Position target){
		if(!board.piece(source).possibleMove(target)) { //se pra peça de origem, a posição de destino não é um movimento possível
			throw new ChessException("The chosen piece can't move to target position"); //a peça escolhida não pode se mover pra posição de destino
		}
	}
	
	private void nextTurn() { //método que troca de turno
		turn++; //incrementação do turno. Turno um passa para o turno dois, e assim por diante
		/*Expressão condicional ternária para mudar a cor da peça a cada turno, se no turno um foi a branca que jogou, no turno dois terá que ser a preta a jogar....*/
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; //(se o jogador atual for igual a branco) ? então agora ele vai ser o preto : caso contrário vai ser o branco
	}
	
	//método que vai receber as coordenadas do Xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //vai receber como valor a posição do xadrez e vai converte-la para posição da matriz através da chamada do método 'toPosition()'
	}
	
	private void initialSetup() { //método responsável por iniciar a partida de Xadrez, colocando as peças no tabuleiro
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
