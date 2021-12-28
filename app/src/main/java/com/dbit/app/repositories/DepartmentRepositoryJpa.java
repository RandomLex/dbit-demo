package com.dbit.app.repositories;

import com.dbit.model.dbit.Department;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;

@Component
public class DepartmentRepositoryJpa extends AbstractRepositoryJpa<Department> implements DepartmentRepository {

    @Override
    protected TypedQuery<Department> findAllQuery() {
        return helper.getEntityManager()
                .createQuery("from Department ", Department.class);
    }
}
