package com.dbit.app.repositories;

import com.dbit.app.exceptions.DatabaseException;
import com.dbit.model.City;
import com.dbit.model.Department;
import com.dbit.model.Employee;
import com.dbit.model.Title;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class EmployeeRepositoryPostgres implements EmployeeRepository {
    //language=SQL
    private static final String SELECT_FROM_EMPLOYEE_ALL_FIELDS =
            "select " +
                    "e.id e_id, e.name e_name, salary, " +
                    "d.id d_id, d.name d_name, " +
                    "c.id c_id, c.name c_name, " +
                    "t.id t_id, t.name t_name " +
                    "from employee e " +
                    "join title t " +
                    "on t.id = e.title_id " +
                    "join department_employee de " +
                    "on e.id = de.employee_id " +
                    "join department d " +
                    "on d.id = de.department_id " +
                    "join city c " +
                    "on d.city_id = c.id";
    private static final String E_ID = "e_id";
    private static final String D_ID = "d_id";
    private static final String C_ID = "c_id";
    private static final String T_ID = "t_id";
    private static final String E_NAME = "e_name";
    private static final String SALARY = "salary";
    private static final String T_NAME = "t_name";
    private static final String D_NAME = "d_name";
    private static final String C_NAME = "c_name";

    private static volatile EmployeeRepositoryPostgres instance;
    private final DataSource dataSource;

    private EmployeeRepositoryPostgres(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EmployeeRepositoryPostgres getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (EmployeeRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryPostgres(dataSource);
                }
            }
        }
        return instance;
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        Map<Integer, Employee> employeeMap = new HashMap<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_FROM_EMPLOYEE_ALL_FIELDS);
             ResultSet rs = ps.executeQuery()) {
            Map<Integer, Department> departmentMap = new HashMap<>();
            Map<Integer, City> cityMap = new HashMap<>();
            Map<Integer, Title> titleMap = new HashMap<>();
            while (rs.next()) {
                int eId = rs.getInt(E_ID);
                int dId = rs.getInt(D_ID);
                int cId = rs.getInt(C_ID);
                int tId = rs.getInt(T_ID);

                employeeMap.putIfAbsent(eId,
                        new Employee()
                                .withId(eId)
                                .withName(rs.getString(E_NAME))
                                .withSalary(rs.getInt(SALARY))
                                .withTitle(putIfAbsentAndReturn(titleMap, tId,
                                        new Title()
                                                .withId(tId)
                                                .withName(rs.getString(T_NAME))))
                                .addDepartment(putIfAbsentAndReturn(departmentMap, dId,
                                        new Department()
                                                .withId(dId)
                                                .withName(rs.getString(D_NAME))
                                                .withCity(putIfAbsentAndReturn(cityMap, cId,
                                                                new City()
                                                                        .withId(cId)
                                                                        .withName(rs.getString(C_NAME)))))));

                employeeMap.computeIfPresent(eId, (id, employee) -> employee.addDepartment(departmentMap.get(dId)));

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Optional<Employee> find(int id) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Optional<Employee> remove(Employee employee) {
        return Optional.empty();
    }

    private static <K, V> V putIfAbsentAndReturn(Map<K, V> map, K key, V value) {
        if (key == null) {
            return null;
        }
        map.putIfAbsent(key, value);
        return map.get(key);
    }
}
