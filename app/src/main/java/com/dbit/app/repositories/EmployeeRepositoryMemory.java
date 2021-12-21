package com.dbit.app.repositories;


import com.dbit.model.Employee;
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
public class EmployeeRepositoryMemory extends AbstractRepositoryMemory<Employee> implements EmployeeRepository {
    private final Map<Integer, Employee> map = new ConcurrentHashMap<>();


    @Override
    public List<Employee> findAll() {
        if (map.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Employee> find(int id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Employee save(Employee employee) {
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
    public Optional<Employee> remove(Employee employee) {
        return Optional.ofNullable(map.remove(employee.getId()));
    }

    {
        map.put(1, new Employee()
                .withId(1)
                .withName("Виктор")
                .withSalary(100));
        map.put(2, new Employee()
                .withId(2)
                .withName("Пётр")
                .withSalary(100));
        map.put(3, new Employee()
                .withId(3)
                .withName("Люда")
                .withSalary(80));
        map.put(4, new Employee()
                .withId(4)
                .withName("Аня")
                .withSalary(150));
        map.put(5, new Employee()
                .withId(5)
                .withName("Дима")
                .withSalary(120));
    }

}
