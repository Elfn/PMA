package com.pma.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Elimane on May, 2020, at 16:16
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request)
    {
       Object status =  request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        //return this.getErrorPath();

        Integer statusCode = (status != null)? Integer.valueOf(status.toString()) : null;

        return (statusCode == HttpStatus.NOT_FOUND.value()) ? "errors/error-404" : (statusCode == HttpStatus.FORBIDDEN.value()) ? "errors/error-403" : (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) ? "errors/error-500" : "errors/error";
    }
}
