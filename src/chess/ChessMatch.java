package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;//associando uma partida de xadrez a um tabuleiro, pois uma partida de Xadrex tem que ter um tabuleiro(composição)
	private boolean check; //propriedade que vai decidir se a peça está em cheque ou não
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>(); //lista de peças no tabuleiro
	private List<Piece> capturedPieces = new ArrayList<>(); //lista de peças capturadas
	
	public ChessMatch() {
		board = new Board(8, 8); //quem tem que saber a dimensão de um tabuleiro de Xadrez, é classe resposável pelas regras do jogo, que no caso é essa. Dimensão do tabuleiro é 8x8.
		turn = 1; //o turno no início da partida vale um.
		currentPlayer = Color.WHITE; //a peça que começa no xadrez é a peça branca, então no primeiro turno será uma peça branca a mover primeiro
		check = false; //uma propriedade booleana começa com false por padrão, coloquei-a no construtor apenas para enfatizar
		initialSetup();//quando se é criada uma nova partida de Xadrez (ChessMatch), se cria um tabuleiro 8x8 e chama o método InitialSetup().
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		/*após o movimento, eu terei que verificar se esse movimento colocou o jogador em cheque*/
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't pput yourself in check"); //você não pode se colocar em cheque//
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false; // (se o teste de cheque do oponente do currentPlayer está em cheque) ? vou dizer que minha partida está em cheque : se não, vou dizer que não está em cheque
		
		if(testCheckMate(opponent(currentPlayer))) { //se está em xeque-mate...
			checkMate = true;
		}
		else {
			nextTurn();//esse método é chamado aqui pois se troca o turno após uma jogada
		}
		return (ChessPiece) capturedPiece; //o DownCasting foi feito pois a capturedPiece era do tipo Piece 
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source); //retirei a peça da sua posição de origem
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target); //eu vou remover a possível peça que está na posição de destino
		board.placePiece(p, target);//colocará a peça a ser movida na posição de destino
		
		if(capturedPiece != null) { //se caso uma peça for capturada
			piecesOnTheBoard.remove(capturedPiece); //eu retiro ela das peças que estão no tabuleiro
			capturedPieces.add(capturedPiece); //e adciono a peça a lista de peças capturadas
		}
		
		return capturedPiece;
		
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) { //método para desfazer o movimento, pois a pessoa não pode se colocar em cheque
		/*A operação se resume em desfazer o que foi feito quando se move uma peça(Método makeMove)*/
		
		ChessPiece p = (ChessPiece)board.removePiece(target);//tira a peça que eu movi para o destino
		p.decreaseMoveCount();
		board.placePiece(p, source); //estou devolvendo a peça para a posição de origem
		
		if(capturedPiece != null) { //caso haja uma peça capturada
			board.placePiece(capturedPiece, target);//está pegando a peça que foi capturada na posição de destino
			capturedPieces.remove(capturedPiece); //vou remover a peça da lista de peças capturadas
			piecesOnTheBoard.add(capturedPiece); //e vou colocar a peça capturada de volta no tabuleiro
		}
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
	
	private Color opponent(Color color) { //esse método vai retornar a cor do oponente da peça atual
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE; //(se a cor for igual a cor branca) ? retorne a cor preta : caso contrário retorne a cor branca
	}
	
	private ChessPiece king(Color color) { //método para pesquisar em jogo, qual que é o rei da cor passada no parâmetro
		
		//já que quem tem a propriedade 'cor' é a classe 'ChessPiece' e estou percorrendo uma lista de peças no tabuleiro do tipo Piece (que é uma classe mais genérica), preciso fazer o downcasting para acessar o atributo de cor			                              
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); //vai filtrar uma cor, e se a cor da peça for a igual passada no parâmetro, vai pega-la 
		for(Piece p : list) { //para cada peça(Piece) 'p' na minha lista 'list' faça...
			if(p instanceof King){//se essas peça 'p' for uma instância de rei
				return (ChessPiece)p;//vai retornar essa peça, ou seja, o rei
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board"); //essa exceção não é para acontecer, então não é necessário trata-la, pois se ela ocorrer, o sistema está com algum problema que necessita de correção
	}
	//método que vai verificar se o rei está em cheque. Este método vai percorrer todas as peças adversárias e averiguar qual delas está com o destino caindo na posição onde está o rei
	private boolean testCheck(Color color) { //o argumento é cor pois estou testando se o rei dessa cor está em cheque
		Position kingPosition = king(color).getChessPosition().toPosition(); //estou pegando a posição do meu rei em formato de matriz
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); //método que vai pegar lista de peças do oponente, ou seja, a cor de todos os oponentes do rei
		for(Piece p : opponentPieces) { //para cada peça 'p', na minha lista de peças do oponente, vou ter que verificar se existe algum movimento possível que leva a posição do meu rei
			boolean[][] mat = p.possibleMoves(); //matriz de movimentos possíveis dessa peça adversária 'p'
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) { //se nessa matriz acima, a posição correspondente a posição do rei, for true, significa que o rei está em cheque.
				return true;
			}
		}
		return false; //se esgotar a verificação da lista de oponentes e nenhum deles está dando cheque no rei, retornará falso, ou seja, o rei não está em cheque
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) { //se essa cor não estiver em xeque, significa que ela também não está em xeque-mate
			return false;
		}		
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList()); //linha de lógica da lista para pegar todas as peças da variável passada no parâmetro, ou seja, vai filtrar as peças pela cor - 'Color' 
		for(Piece p : list) { //'for' para percorrer todas as peças 'p' pertencentes a lista. Então dentro, se existir uma peça 'p' dentro dessa lista que possua um movimento que tira do xeque, eu retorno 'false' dentro do 'for'
			boolean[][] mat = p.possibleMoves();
			for(int i = 0;i<board.getRows();i++) {
				for(int j = 0;j<board.getColumns();j++) {
					if(mat[i][j]) { //se essa posição é um movimento possível e tira do xeque..
						Position source = ((ChessPiece)p).getChessPosition().toPosition();//Para acessar a posição da peça, tenho que fazer um downcasting para acessar a posição de xadrez (getChessPosition) e após isso converte-la para posição de matriz
						Position target = new Position(i, j); //posição de destino da peça
						Piece capturedPiece = makeMove(source, target); //método para mover a peça
						boolean testCheck = testCheck(color); //vai testar se o rei da minha cor está em xeque
						undoMove(source, target, capturedPiece);//desfazer o movimento, pois o movimento acima foi para testar se há a possibilidade de sair do xeque
						if(!testCheck) { //se não está em xeque, retorne 'false'...
							return false;
						}
					}
				}
			}
		}
		return true; //xeque-mate
	}
	
	//método que vai receber as coordenadas do Xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition()); //vai receber como valor a posição do xadrez e vai converte-la para posição da matriz através da chamada do método 'toPosition()'
        piecesOnTheBoard.add(piece); //sempre que eu for instanciar uma nova peça no meu jogo de xadrez, terei que colocar essa peça dentro da lista de peças do meu tabuleiro
	}
	
	private void initialSetup() { //método responsável por iniciar a partida de Xadrez, colocando as peças no tabuleiro
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));

       
	}

}
