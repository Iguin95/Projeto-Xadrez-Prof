package chess;

import boardgame.Position;

public class ChessPosition { //vai indicar a posição da peça de acordo com as coordenadas reais do tabuleiro, e não da matriz
	
	//outro sistema de coordenadas
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

	/*
	 *  As linhas na matriz correspondem ao primeiro valor (que no caso é zero na matriz e 8 no Xadrez) menos (operação de subtração) a posição desejada.
	 *  Por exemplo, se eu quero uma peça na posição 'b6', eu teria que subtrair 8 por 6 que daria o valor 2, que seria a linha 2 da matriz. 
	 *  E para achar a coluna 'b' não seria diferente, pois subtraindo 'b' por 'a' (já que 'a' é o primeiro elemento e na tabela Unicode tem o valor menor do alfabeto, todos terão que subtrair com ele pra encontrarem sua posição na matriz), também consigo achar a posição na matriz, já que o 'a' na tabela Unicode tem o valor de 41 e 'b' na tabela Unicode, tem o valor de 42. 
	 *  Então sua subtração daria o resultado de 1, o que seria a posição na matriz.
	 */
	protected Position toPosition() { //Esse método converte a posição de Xadrez para matriz, necessário para que quando seja chamado, a peça ficar na posição correta, já que se trata de uma matriz.
		return new Position(8 - row, column - 'a'); 
	}
	
	protected static ChessPosition fromPosition(Position position) { //Esse método irá converter a posição da matriz em posição do xadrez e ira retornar uma nova posição
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row; //O string vazio "" é usado para forçar o compilador a aceitar a concatenação de Strings
	}
	

}
