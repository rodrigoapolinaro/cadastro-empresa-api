package com.apolinaro.empresa.service.exception;

public class EmpresaNaoEncontradaExcepton extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpresaNaoEncontradaExcepton(String message) {
		super(message);
	}
	
	public EmpresaNaoEncontradaExcepton(String message, Throwable cause) {
		super(message, cause);
	}

}
