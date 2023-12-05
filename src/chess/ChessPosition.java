package chess;

import boardgame.Position;

public class ChessPosition {
	
	
	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		/*programação defensiva*/
		if(column < 'a' || column > 'h' || row < 1 || row > 8) { //se a coluna for menor que 'a' ou maior que 'h', ou também as linha for menor do que 1 ou maior do que 8, será lançada a exceção
			throw new ChessException("Error instatiating ChessPosition. Valid values are form a1 to h8.");//"Erro ao instanciar ChessPosition. Os valores válidos vão de a1 a h8."//
		}
		this.column = column;
		this.row = row;
	}
	

	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}

	
	protected Position toPosition() {
		return new Position(8 - row, column - 'a'); 
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	

}
