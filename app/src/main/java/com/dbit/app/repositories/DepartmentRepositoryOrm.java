package com.dbit.app.repositories;

import com.dbit.model.dbit.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepositoryOrm extends AbstractRepositoryOrm<Department> implements DepartmentRepository {

    public DepartmentRepositoryOrm() {
        clazz = Department.class;
    }
}
