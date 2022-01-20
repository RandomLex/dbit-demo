package com.dbit.app.repositories;

import com.dbit.model.dbit.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();


}
