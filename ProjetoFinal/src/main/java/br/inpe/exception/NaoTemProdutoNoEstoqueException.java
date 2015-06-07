package br.inpe.exception;

public class NaoTemProdutoNoEstoqueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoTemProdutoNoEstoqueException() {
		super();
	}

	public NaoTemProdutoNoEstoqueException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NaoTemProdutoNoEstoqueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NaoTemProdutoNoEstoqueException(String message) {
		super(message);
	}

	public NaoTemProdutoNoEstoqueException(Throwable cause) {
		super(cause);
	}

}
