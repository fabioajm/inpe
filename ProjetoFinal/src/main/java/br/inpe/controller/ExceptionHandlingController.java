package br.inpe.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(Throwable.class)
	public String gpuErrorGeneric(Throwable e){
		e.printStackTrace();
		return e.getMessage();
	}
	
	
}
