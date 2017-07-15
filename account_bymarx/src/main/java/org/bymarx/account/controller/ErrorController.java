package org.bymarx.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 失败页面控制器
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends WebExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFund() throws Exception {
        return "error/404";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public Object errorServer() throws Exception {
        return "error/500";
    }
}
