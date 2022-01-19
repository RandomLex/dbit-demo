package com.dbit.app.services;

import com.dbit.app.repositories.EmployeeRepository;
import com.dbit.model.dbit.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class EmployeeService extends AbstractService<Employee> {

    public EmployeeService(@Qualifier("employeeRepositoryJpa") EmployeeRepository employeeRepository, TransactionTemplate transactionTemplate) {
        super(employeeRepository, transactionTemplate);
    }


}
