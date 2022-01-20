package com.dbit.app.services;

import com.dbit.app.repositories.Repository;
import com.dbit.model.dbit.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
public abstract class AbstractService<T extends AbstractEntity> implements ServiceInterface<T> {

    protected final Repository<T> repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Optional<T> get(int id) {
        return repository.find(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<T> delete(T entity) {
        return repository.remove(entity);
    }
}
