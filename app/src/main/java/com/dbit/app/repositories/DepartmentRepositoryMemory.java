package com.dbit.app.repositories;


import com.dbit.model.Department;
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
public class DepartmentRepositoryMemory extends AbstractRepositoryMemory<Department> implements DepartmentRepository {
    private final Map<Integer, Department> map = new ConcurrentHashMap<>();


    @Override
    public List<Department> findAll() {
        if (map.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Department> find(int id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Department save(Department department) {
        Integer id = department.getId();
        if (id == null) {
            id = generateId();
            department.setId(id);
        }
        map.put(id, department);
        return department;
    }

    private int generateId() {
        int id;
        do {
            id = ThreadLocalRandom.current().nextInt(1, 1_000);
        } while (map.containsKey(id));
        return id;
    }

    @Override
    public Optional<Department> remove(Department department) {
        return Optional.ofNullable(map.remove(department.getId()));
    }

    {
        map.put(1, new Department()
                .withId(1)
                .withName("Финансов"));
        map.put(2, new Department()
                .withId(2)
                .withName("Перевозок"));
    }

}
