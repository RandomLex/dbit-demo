package com.dbit.app.repositories;

import com.dbit.model.dbit.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public abstract class AbstractRepositoryOrm<T extends AbstractEntity> implements Repository<T> {
    protected Class<T> clazz;
    private EntityManagerFactory emf;
    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<T> findAll() {
        begin();
        List<T> resultList = getEm().createQuery("from " + clazz.getName(), clazz).getResultList();
        commit();
        return resultList;
    }

    @Override
    public Optional<T> find(int id) {
        begin();
        Optional<T> optionalEntity = Optional.ofNullable(getEm().find(clazz, id));
        commit();
        return optionalEntity;
    }

    @Override
    public T save(T entity) {
        begin();
        if (entity.getId() == null) {
            getEm().persist(entity);
        } else {
            getEm().merge(entity);
        }
        commit();
        return entity;
    }

    @Override
    public Optional<T> remove(T entity) {
        begin();
        Optional<T> foundEntityOptional = find(entity.getId());
        if (foundEntityOptional.isPresent()) {
            getEm().remove(entity);
            commit();
            return foundEntityOptional;
        }
        commit();
        return Optional.empty();
    }

    public EntityManager getEm() {
        if (em.get() == null) {
            em.set(emf.createEntityManager());
        }
        return em.get();
    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }

    public void rollBack() {
        getEm().getTransaction().rollback();
    }
}
