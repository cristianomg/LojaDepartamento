package Exceptions;

public class VendaNaoEncontradaException extends Exception{
	private static final long serialVersionUID = -630594161825952236L;

	public VendaNaoEncontradaException(String msg) {
		super(msg);
	}
}
