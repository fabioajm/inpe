package br.inpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(Throwable.class)
	public String gpuErrorGeneric(Throwable e, HttpServletRequest httpServletRequest){
		e.printStackTrace();
		httpServletRequest.getRequestURI();
		return "/index";
	}
	
	
}
