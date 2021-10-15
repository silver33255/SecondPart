package com.panaskin.secureapp.logger;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.JoinPoint;

@Log4j2
@Aspect
public class ServiceLoggerAdvice {
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable{
        long beforeMethodTime = System.nanoTime();
        Object result = jp.proceed();
        long afterMethodTime = System.nanoTime();
        log.info("Method " + jp.getSignature().getName()
                + " was executed per: " + (afterMethodTime - beforeMethodTime) + " nanos");
        return result;
    }
}
