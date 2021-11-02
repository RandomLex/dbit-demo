package com.dbit.app.repositories;

import com.dbit.model.AbstractEntity;
import com.dbit.model.Employee;

import javax.persistence.TypedQuery;
import javax.sql.DataSource;

public class EmployeeRepositoryJpa extends AbstractRepositoryJpa<Employee> implements EmployeeRepository {
    private static volatile EmployeeRepositoryJpa instance;

    private EmployeeRepositoryJpa() {
        //singleton
    }

    public static EmployeeRepositoryJpa getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryJpa.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryJpa();
                }
            }
        }
        return instance;
    }

    @Override
    protected TypedQuery<Employee> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Employee ", Employee.class);
    }
}
