package com.dbit.app.aspect;

import com.dbit.app.exceptions.DatabaseException;
import com.dbit.app.repositories.EntityManagerHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class JpaTransactionAspect {
    private final EntityManagerHelper helper;

    @SneakyThrows
    @Around("@annotation(JpaTransaction)")
    public Object transaction(ProceedingJoinPoint jp) {
        log.debug("Transaction : {}", jp.getSignature().getName());
        Object result = null;
        EntityManager em = null;

        try {
            em = helper.getEntityManager();
            em.getTransaction().begin();

            result = jp.proceed();

            em.getTransaction().commit();
            em.close();

        } catch (Exception e) {
            safeRollback(em, e);
        } finally {
            quietCloseEntityManager(em);
        }
        return result;
    }

    private void safeRollback(EntityManager em, Exception e) {
        log.error(e.getMessage(), e);
        if (em != null) {
            em.getTransaction().rollback();
        }
        throw new DatabaseException(e);
    }

    private void quietCloseEntityManager(EntityManager em) {
        if (em != null) {
            try {
                em.close();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                //doNothing
            }
        }
    }

}
