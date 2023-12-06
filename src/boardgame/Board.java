package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	//A classe Position é acessada diretamente nos argumentos das funções

	public Board(int rows, int columns) {
		/*Programação defensiva*/
		if(rows < 1 || columns < 1) { //o tabuleiro só será criada se existir pelo menos uma linha e uma coluna
			throw new BoardException("Error creating board: there must be at least  row and 1 column");//"Erro ao criar o quadro: deve haver pelo menos uma linha e uma coluna"//
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //é uma obrigatoriedade a criação de uma matriz da peça, ou seja, assim que o tabuleiro (Board) é criado, a peça recebe uma posição
	}

	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	
	public Piece piece(int row, int column) { //método para retornar uma peça dada uma linha e uma coluna
		/*Programação defensiva*/
		if(!positionExists(row, column)) { //se essa posição não existir, lance a exceção
			throw new BoardException("Position not on the board");//"Posição fora do tabuleiro"//
		}
		return pieces[row][column];
	}
	
	
	public Piece piece(Position position) { //método para retornar a peça pela posição
		/*Programação defensiva*/
		if(!positionExists(position)) { //se essa posição não existir, lance a exceção
			throw new BoardException("Position not on the board");//"Posição fora do tabuleiro"//
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	public void placePiece(Piece piece, Position position) { //método resposável por colocar a peça de Xadrez no devido lugar selecionado. O método está recebendo(nos seus argumentos), uma peça e uma posição
		/*Programação defensiva*/
		if(thereIsAPiece(position)) { //se existe uma peça nessa posição, não posso colocar uma nova peça lá
			throw new BoardException("There is already a piece on position " + position); //"Já existe uma peça em posição"//
		}
		pieces[position.getRow()][position.getColumn()] = piece; //atribuir a posição dada na matriz 'pieces', a peça informada. Essa matriz é chamada no construtor desta classe 
		piece.position = position;//no caso, tenho que informar que a peça existe, ou seja, não está em uma posição nula. Então, graças a visibilidade 'protected' do atributo 'position' da classe Piece, posso atribuir a sua posição a posição informada nesse método através do argumento 'position' dentro desse escopo
	}
	
	
	
	private boolean positionExists(int row, int column) { //a criação desse método se deve ao fato de que em dado momento, será mais simples testar a posição pela linha e pela coluna do que propriamente pela posição
		return row >= 0 && row < rows && column >= 0 && column < columns; //a posição existe ("positionExists") quando ela está dentro do tabuleiro, ou seja, quando a linha e a coluna são maiores ou iguais a zero e quando a linha é menor que a altura do tabuleiro ("rows") e quanto a coluna é menor que a quantidade de colunas do tabuleiro ("columns")
	}
	
	
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn()); //está reaproveitando o método de cima
	}
	
	          
	               /*há uma peça*/
	public boolean thereIsAPiece(Position position) { 
		/*Programação defensiva*/
		if(!positionExists(position)) {//antes de testar se há uma peça, testa primeira se a posição existe
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null; //para verificar se existe uma peça na posição, a peça preicsa ser diferente de null. Lembrando que o método chamado aqui é o método que retorna a peça pela posição
	}

}
