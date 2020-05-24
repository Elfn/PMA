package com.pma.app.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Elimane on May, 2020, at 23:50
 */
/*
*
* In computing, aspect-oriented programming (AOP) is
* a programming paradigm that aims to increase modularity by allowing
* the separation of cross-cutting concerns
*  --------------------------------------------------------------------
* @Cross-cutting concerns are pieces of functionality
* that are used across multiple parts of a system
* ---------------------------------------------------------------------
* AOP, aspects enable the modularization of concerns such as transaction management,
* logging or security that cut across multiple types and objects (often termed crosscutting concerns).
*
* */
//Here we are defining the format we want logging to happen
@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //All components in the package below will be affected
    //By that code
    @Pointcut("within(com.pma.app.controllers..*)")
    public void definePackagePointcuts(){

    }
//    @Pointcut("within(com.pma.app.controllers..*) || within(com.pma.app.dao..*)")
//    public void definePackagePointcuts(){
//
//    }

    //This method will run after every single thing in the "controllers" package
//    @Before("definePackagePointcuts()")
//    public void logBefore(JoinPoint jp) {
//        log.debug("\n \n \n");
//        log.debug("-----------BEFORE METHOD EXECUTION----------- \n {} => {}() with arguments[s] = {}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//        log.debug("_____________________________\n \n \n");
//    }

    //@Around is to have more control on joinpoints
    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable {
        log.debug("\n \n \n");
        log.debug("-----------BEFORE METHOD EXECUTION----------- \n {} => {}() with arguments[s] = {}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug("_____________________________\n \n \n");

        Object o = jp.proceed();

        log.debug("\n \n \n");
        log.debug("-----------AFTER METHOD EXECUTION----------- \n {} => {}() with arguments[s] = {}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug("_____________________________\n \n \n");

        return o;
    }
}
