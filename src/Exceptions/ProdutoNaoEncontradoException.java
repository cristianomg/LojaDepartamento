package Exceptions;

public class ProdutoNaoEncontradoException extends Exception {
	private static final long serialVersionUID = 5694617941513895346L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
	}
}
