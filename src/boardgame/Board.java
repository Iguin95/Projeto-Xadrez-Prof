package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; //é uma obrigatoriedade a criação de uma matriz da peça, ou seja, assim que o tabuleiro (Board) é criado, a peça recebe uma posição
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece(int row, int column) { //método para retornar uma peça dada uma linha e uma coluna
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { //método para retornar a peça pela posição
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) { //método resposável por colocar a peça de Xadrez no devido lugar selecionado. O método está recebendo(nos seus argumentos), uma pela e uma posição
		pieces[position.getRow()][position.getColumn()] = piece; //atribuir a posição dada na matriz 'pieces', a peça informada. Essa matriz é chamada no construtor desta classe 
		piece.position = position;//no caso, tenho que informar que a peça existe, ou seja, não está em uma posição nula. Então, graças a visibilidade 'protected' do atributo 'position' da classe Piece, posso atribuir a sua posição a posição informada nesse método através do argumento 'position' dentro desse escopo
	}

}
