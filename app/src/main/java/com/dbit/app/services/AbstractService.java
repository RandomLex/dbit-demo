package com.dbit.app.services;

import com.dbit.app.repositories.Repository;
import com.dbit.model.dbit.AbstractEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class AbstractService<T extends AbstractEntity> implements ServiceInterface<T> {

    protected final Repository<T> repository;
    protected final TransactionTemplate transactionTemplate;

    @Override
    public List<T> getAll() {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return repository.findAll();
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
            }
            return new ArrayList<>();
        });
    }

    @Override
    public Optional<T> get(int id) {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return repository.find(id);
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
            }
            return Optional.empty();
        });
    }

    @Override
    public T save(T entity) {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return repository.save(entity);
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
            }
            return null;
        });
    }

    @Override
    public Optional<T> delete(T entity) {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return repository.remove(entity);
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
            }
            return Optional.empty();
        });
    }
}
