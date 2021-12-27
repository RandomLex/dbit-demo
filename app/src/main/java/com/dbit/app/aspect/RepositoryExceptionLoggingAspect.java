package com.dbit.app.aspect;

import com.dbit.app.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RepositoryExceptionLoggingAspect {


    @AfterThrowing(
            pointcut = "execution(* com.dbit.app.repositories.AbstractRepositoryMemory.findAll())",
            throwing = "ex"
    )
    public void loggingExceptionWithRethrowing(Exception ex) {
        log.error("Exception {}", ex.getMessage());
        throw new ApplicationException(ex);
    }
}
