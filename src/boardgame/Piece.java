package boardgame;

public class Piece { //peça
	
	protected Position position; //esse tipo de posição, não é uma posição do Xadrez, ela é uma posição de matriz. Ela é protegida para que ela não seja vista por outros pacotes como na camada de Xadrez.
	private Board board; //a peça(piece) é associada ao tabuleiro(board)
	
	public Piece(Board board) { //nesse caso, eu passo somente o tabuleiro na criação da peça, porque a posição de uma peça recém criada é tida como nula, pra mostrar que ela não foi colocada no tabuleiro ainda.
		this.board = board;
		position = null; //posição de uma peça recém criada. Não a necessidade de ter essa linha, pois o Java já a coloca como 'null'.
	}

	protected Board getBoard() { //somente classes do mesmo pacote e subclasses é que poderão acessar o tabuleiro de uma mesma peça. Há somente o método 'get' pois não posso permitir a alteração do tabuleiro.
		return board;
	}


	

}
