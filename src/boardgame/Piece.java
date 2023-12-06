package boardgame;

public abstract class Piece { //peça
	
	protected Position position; //esse tipo de posição, não é uma posição do Xadrez, ela é uma posição de matriz. Ela é protegida para que ela não seja vista por outros pacotes como na camada de Xadrez.
	private Board board; //a peça(piece) é associada ao tabuleiro(board)
	
	public Piece(Board board) { //nesse caso, eu passo somente o tabuleiro na criação da peça, porque a posição de uma peça recém criada é tida como nula, pra mostrar que ela não foi colocada no tabuleiro ainda.
		this.board = board;
		position = null; //posição de uma peça recém criada. Não a necessidade de ter essa linha, pois o Java já a coloca como 'null'.
	}

	protected Board getBoard() { //somente classes do mesmo pacote e subclasses é que poderão acessar o tabuleiro de uma mesma peça. Há somente o método 'get' pois não posso permitir a alteração do tabuleiro.
		return board;
	}


	public abstract boolean[][] possibleMoves(); //esta operação é abstrata pois é um tipo genérico. Cada peça tem sua própria movimentação, então não posso montar uma lógica de movimentação de somente uma peça, tem que ser geral

	
	//método concreto que está usando um método abstrato. Isso também é chamado de hook methods (Um método que faz um 'gancho' com a subclasse) 
	public boolean possibleMove(Position position) { //método que vai verificar se é possível mexer a peça para dada posição
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() { //vai conferir se existe pelo menos uma posição disponível para que a peça possa se locomover. Método útil para verificar se a peça não está travada, ou seja, sem possibilidade de se mover para qualquer direção
		boolean[][] mat = possibleMoves();
		for(int i = 0;i < mat.length; i++) {
			for(int j = 0;j < mat.length; j++) {
				if(mat[i][j]) {//se existir posição na matriz, irá retornar true
					return true;
				}
			}
		}
		return false; //se não houver posições retornará false
	}
}
