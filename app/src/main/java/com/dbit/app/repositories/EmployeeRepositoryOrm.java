package com.dbit.app.repositories;

import com.dbit.model.dbit.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepositoryOrm extends AbstractRepositoryOrm<Employee> implements EmployeeRepository {

    public EmployeeRepositoryOrm() {
        clazz = Employee.class;
    }
}
