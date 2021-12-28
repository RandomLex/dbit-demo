package com.dbit.app.repositories;

import com.dbit.model.dbit.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeRepositoryTemplate implements EmployeeRepository {
    //language=SQL
    private static final String SELECT_FROM_EMPLOYEE_ALL_FIELDS =
            "select " +
                    "e.id e_id, e.name e_name, salary, " +
                    "d.id d_id, d.name d_name, " +
                    "c.id c_id, c.name c_name, " +
                    "t.id t_id, t.name t_name " +
                    "from employee e " +
                    "left outer join title t " +
                    "on t.id = e.title_id " +
                    "left outer join department_employee de " +
                    "on e.id = de.employee_id " +
                    "left outer join department d " +
                    "on d.id = de.department_id " +
                    "left outer join city c " +
                    "on d.city_id = c.id";
    //language=SQL
    private static final String ONE_ENTITY_FILTER = " where e.id = ?";
    //language=SQL
    private static final String FIND_EMPLOYEE_BY_ID = SELECT_FROM_EMPLOYEE_ALL_FIELDS + ONE_ENTITY_FILTER;

    private static final String E_ID = "e_id";
    private static final String T_ID = "t_id";
    private static final String E_NAME = "e_name";
    private static final String SALARY = "salary";
    private static final String T_NAME = "t_name";

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> employeeRowMapper = (rs, rowNum) -> new Employee()
            .withId(rs.getInt(E_ID))
            .withName(rs.getString(E_NAME))
            .withSalary(rs.getInt(SALARY));
//            .withTitle(new Title()
//                    .withId(rs.getInt(T_ID))
//                    .withName(rs.getString(T_NAME)));
//

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_FROM_EMPLOYEE_ALL_FIELDS, employeeRowMapper);
    }

    @Override
    public Optional<Employee> find(int id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID, employeeRowMapper, id));
    }

    @Override
    public Employee save(Employee entity) {
        Integer id = jdbcTemplate.queryForObject("insert into employee (name, salary) values (?, ?) returning id",
                (rs, rowNum) -> rs.getInt("id"), entity.getName(), entity.getSalary());
        return entity.withId(id);
    }

    @Override
    public Optional<Employee> remove(Employee entity) {
        return Optional.empty();
    }
}
