package boardgame;

public class BoardException extends RuntimeException { //RuntimeException pra ser opcional o tratamento da exceção
	private static final long serialVersionUID = 1L;
	
	public BoardException(String msg) {
		super(msg); //este construtor vai repassar a mensagem para o construtor da super classe RuntimeException
	}

}
