package com.favcode.favschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.favcode.favschool..*.*(..))")
    public Object log(ProceedingJoinPoint jointPoint) throws Throwable{
        log.info(jointPoint.getSignature().toString() + "method execution start");
        Instant start = Instant.now();
        Object returnObj = jointPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start,finish).toMillis();
        log.info("Time took to execute: " + jointPoint.getSignature().toString() + " method is: " + timeElapsed);
        log.info(jointPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.favcode.favschool.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature().toString() + " An exception happened due to: " + ex.getMessage());
    }
}
