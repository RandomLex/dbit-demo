package com.dbit.app.services;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> getAll();
    Optional<T> get(int id);
    T save(T entity);
    Optional<T> delete(T entity);
}
