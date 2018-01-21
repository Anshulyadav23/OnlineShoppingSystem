package net.ag.shoppersstop.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException()
	{
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not constructed");
		mv.addObject("errorDescription", "The page you are looking is not available now!");
		mv.addObject("title", "404 Error Page");
		
		return mv;
		
	 }
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductnotFoundException()
	{
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product is not available");
		mv.addObject("errorDescription", "The product you are looking for is not available now!");
		mv.addObject("title", "Product Unavailable");
		
		return mv;
		
	 }
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex)
	{
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact your administrator");
		
		/*only for debugging*/
		
		StringWriter sr = new StringWriter();
		PrintWriter pw = new PrintWriter(sr);
		ex.printStackTrace(pw);
		
		
		mv.addObject("errorDescription", sr.toString());
		mv.addObject("title", "Error");
		
		return mv;
		
	 }
	
}
