package chess;

public class ChessException extends RuntimeException { //Exceção da camada Chess (Organização em camadas)
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	}
}
