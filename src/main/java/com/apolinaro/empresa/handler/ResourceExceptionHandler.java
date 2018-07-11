package com.apolinaro.empresa.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apolinaro.empresa.model.DetalhesErro;
import com.apolinaro.empresa.service.exception.EmpresaNaoEncontradaExcepton;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EmpresaNaoEncontradaExcepton.class)
	public ResponseEntity<DetalhesErro> handleEmpresaNaoEncontradaException(EmpresaNaoEncontradaExcepton e,
			HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Empresa não pode ser encontrada");
		erro.setMensagemDesenvolvedor("http://erros.apolinaro-empresa-api.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
