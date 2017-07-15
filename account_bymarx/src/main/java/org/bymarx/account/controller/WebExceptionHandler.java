package org.bymarx.account.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by howq on 2017/3/11.
 */
@ControllerAdvice
public class WebExceptionHandler extends BaseController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView resolveException(HttpServletRequest request, Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ModelAndView("error/404");
    }
}
