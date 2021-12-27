package com.dbit.app.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

//    @Before("execution(* com.dbit.app.controllers.*.*(..))")
//    public void before(JoinPoint jp) {
//        log.info("Logging before controller's method {}", jp.getSignature().getName());
//    }

    @Pointcut("execution(* com.dbit.app.controllers.*.*(..))")
    public void controllers() {
        // pointcut
    }

//    @Before("controllers()")
    public void before(JoinPoint jp) {
        log.info("Logging before controller's method: {}", jp.getSignature().getName());
    }

//    @After("controllers()")
    public void after(JoinPoint jp) {
        log.info("Logging after controller's method: {}", jp.getSignature().getName());
    }

    @SneakyThrows
//    @Around("controllers()")
    public Object around(ProceedingJoinPoint jp) {
        log.info("Logging around before method: {}", jp.getSignature().getName());
        log.info("----------------------------");
        log.info(jp.getKind());
        log.info(jp.toLongString());
        log.info(jp.toShortString());
        Arrays.stream(jp.getArgs()).forEach(System.out::println);
        log.info(jp.getStaticPart().toLongString());
        log.info(jp.getThis().toString());
        Object result = jp.proceed();
        log.info("----------------------------");
        log.info("Logging around after method: {}", jp.getSignature().getName());
        return result;
    }


}
