package com.dbit.app.services;

import com.dbit.app.repositories.EmployeeCrudRepository;
import com.dbit.app.repositories.EmployeeRepository;
import com.dbit.model.dbit.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Service
public class EmployeeService extends AbstractService<Employee> {

    @Autowired
    private EmployeeCrudRepository crudRepository;

    public EmployeeService(@Qualifier("employeeRepositoryOrm") EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    @Transactional
    public Optional<Employee> getEmployee(Integer id) {
        return crudRepository.findById(id);
    }
}
