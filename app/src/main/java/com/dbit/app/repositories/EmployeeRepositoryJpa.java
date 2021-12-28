package com.dbit.app.repositories;

import com.dbit.model.dbit.Employee;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class EmployeeRepositoryJpa extends AbstractRepositoryJpa<Employee> implements EmployeeRepository {

    @Override
    protected TypedQuery<Employee> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee ", Employee.class);
    }
}
