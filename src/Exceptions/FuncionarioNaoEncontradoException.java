package Exceptions;

public class FuncionarioNaoEncontradoException extends Exception{
	private static final long serialVersionUID = -192955654534594984L;
	public FuncionarioNaoEncontradoException(String msg) {
		super(msg);
	}
}
