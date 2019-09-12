package Exceptions;

public class ClienteNaoEncontradoException extends Exception{
	private static final long serialVersionUID = -6027896485860639795L;

	public ClienteNaoEncontradoException(String msg) {
		super(msg);
	}
}
