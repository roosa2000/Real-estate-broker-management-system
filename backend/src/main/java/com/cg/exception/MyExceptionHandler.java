package com.cg.exception;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*************************************************************************************
 * @author                 SANJAY DAS
 * Description             It is a custom exception handler class that handles the exception occurring at 
 *                         service level.
 * @version                1.0
 * @since           	   24-03-2021
 *
 ***************************************************************************************/

@ControllerAdvice
public class MyExceptionHandler {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ Exception.class })
	public ErrorMapper handleConflict(Exception ex, HttpServletRequest req) {
		String msg = ex.getMessage();
		String uri = req.getRequestURL().toString();
		return new ErrorMapper(uri, msg, new Date());
	}
}
