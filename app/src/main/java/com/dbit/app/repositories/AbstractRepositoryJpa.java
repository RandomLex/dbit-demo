package com.dbit.app.repositories;

import com.dbit.app.aspect.JpaTransaction;
import com.dbit.app.exceptions.DatabaseException;
import com.dbit.model.dbit.AbstractEntity;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractRepositoryJpa<T extends AbstractEntity> implements Repository<T> {
    protected final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    protected abstract TypedQuery<T> findAllQuery();

    @Override
    @JpaTransaction
    public List<T> findAll() {
        return findAllQuery().getResultList();
    }

    @Override
    public Optional<T> find(int id) {
        EntityManager em = helper.getEntityManager();
        em.getTransaction().begin();

        T entity = em.find(getType(), id);

        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(em.find(getType(), id));
    }

    @Override
    public T save(T entity) {
        EntityManager em = helper.getEntityManager();
        em.getTransaction().begin();
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public Optional<T> remove(T entity) {
        EntityManager em = helper.getEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(entity);
    }

    private Class<T> getType() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        return  (Class) type.getActualTypeArguments()[0];
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
