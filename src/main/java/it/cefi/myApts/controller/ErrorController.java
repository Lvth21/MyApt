package it.cefi.myApts.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(Exception.class)
	
	public ModelAndView handlerError(Exception e)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("error", e.getMessage());
		model.setViewName("error");
		return model;
	}
}
