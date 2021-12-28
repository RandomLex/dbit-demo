package com.dbit.app.repositories;


import com.dbit.model.dbit.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
public class AbstractRepositoryMemory<T extends AbstractEntity> implements Repository<T> {
    private final Map<Integer, T> map = new ConcurrentHashMap<>();


    @Override
    public List<T> findAll() {
        if (map.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<T> find(int id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public T save(T employee) {
        Integer id = employee.getId();
        if (id == null) {
            id = generateId();
            employee.setId(id);
        }
        map.put(id, employee);
        return employee;
    }

    private int generateId() {
        int id;
        do {
            id = ThreadLocalRandom.current().nextInt(1, 1_000);
        } while (map.containsKey(id));
        return id;
    }

    @Override
    public Optional<T> remove(T employee) {
        return Optional.ofNullable(map.remove(employee.getId()));
    }



}
