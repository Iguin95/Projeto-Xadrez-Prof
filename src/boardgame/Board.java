package boardgame;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least  row and 1 column");
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
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { //método para retornar a peça pela posição
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) { //método resposável por colocar a peça de Xadrez no devido lugar selecionado. O método está recebendo(nos seus argumentos), uma pela e uma posição
		if(thereIAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; //atribuir a posição dada na matriz 'pieces', a peça informada. Essa matriz é chamada no construtor desta classe 
		piece.position = position;//no caso, tenho que informar que a peça existe, ou seja, não está em uma posição nula. Então, graças a visibilidade 'protected' do atributo 'position' da classe Piece, posso atribuir a sua posição a posição informada nesse método através do argumento 'position' dentro desse escopo
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}

}
