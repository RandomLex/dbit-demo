package com.dbit.app.repositories;

import com.dbit.model.dbit.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public abstract class AbstractRepositoryOrm<T extends AbstractEntity> implements Repository<T> {
    protected Class<T> clazz;
//    @PersistenceUnit
//    private EntityManagerFactory emf;\


//    @PersistenceContext(type = PersistenceContextType.TRANSACTION) //commented as soon it is set by default
    @PersistenceContext
    private EntityManager em;

//    private final ThreadLocal<EntityManager> em = new ThreadLocal<>();
//
//    @Autowired
//    public void setEmf(EntityManagerFactory emf) {
//        this.emf = emf;
//    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    @Override
    public Optional<T> find(int id) {
        return Optional.ofNullable(em.find(clazz, id));
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<T> remove(T entity) {
        Optional<T> foundEntityOptional = find(entity.getId());
        if (foundEntityOptional.isPresent()) {
            em.remove(foundEntityOptional.get());
            return Optional.of(entity);
        }
        return Optional.empty();
    }

}
