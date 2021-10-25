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
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    //language=SQL
    private static final String ONE_ENTITY_FILTER = " where e.id = ?";
    //language=SQL
    private static final String FIND_EMPLOYEE_BY_ID = SELECT_FROM_EMPLOYEE_ALL_FIELDS + ONE_ENTITY_FILTER;

    private static final String E_ID = "e_id";
    private static final String D_ID = "d_id";
    private static final String C_ID = "c_id";
    private static final String T_ID = "t_id";
    private static final String E_NAME = "e_name";
    private static final String SALARY = "salary";
    private static final String T_NAME = "t_name";
    private static final String D_NAME = "d_name";
    private static final String C_NAME = "c_name";
    private static final int POSITION_ID = 1;
    private static final String INSERT_EMPLOYEE_SQL = "insert into employee (name, salary) values (?, ?) returning id";
    //language=SQL
    private static final String UPDATE_EMPLOYEE_SQL = "update employee e set name = ?, salary = ?" + ONE_ENTITY_FILTER;
    //language=SQL
    private static final String DELETE_EMPLOYEE_BY_ID = "delete from employee e" + ONE_ENTITY_FILTER;

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
        List<Employee> result;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_FROM_EMPLOYEE_ALL_FIELDS);
             ResultSet rs = ps.executeQuery()) {
            result = resultSetToEmployees(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
        return result;
    }

    @Override
    public Optional<Employee> find(int id) {
        List<Employee> result;
        ResultSet rs = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
            ps.setInt(POSITION_ID, id);
            rs = ps.executeQuery();
            result = resultSetToEmployees(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        } finally {
            closeQuietly(rs);
        }
        return result.stream().findAny();
    }

    private static void closeQuietly(AutoCloseable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            log.error("Couldn't close {}", closeable);
        }
    }

    @Override
    public Employee save(Employee employee) {
        return employee.getId() == null ? insert(employee) : update(employee);
    }

    private Employee insert(Employee employee) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return employee.withId(rs.getInt(POSITION_ID));
            }
            return null;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    private Employee update(Employee employee) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setInt(3, employee.getId());
            if (ps.executeUpdate() > 0) {
                return employee;
            }
            return null;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Employee> remove(Employee employee) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {
            ps.setInt(1, employee.getId());
            if (ps.executeUpdate() > 0) {
                return Optional.of(employee);
            }
            return Optional.empty();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
    }

    private List<Employee> resultSetToEmployees(ResultSet rs) throws SQLException {
        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();
        Map<Integer, Department> departmentMap = new LinkedHashMap<>();
        Map<Integer, City> cityMap = new LinkedHashMap<>();
        Map<Integer, Title> titleMap = new LinkedHashMap<>();
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
        Collection<Employee> values = employeeMap.values();
        return values.isEmpty() ? new ArrayList<>() : new ArrayList<>(values);
    }

    private static <K, V> V putIfAbsentAndReturn(Map<K, V> map, K key, V value) {
        if (key == null) {
            return null;
        }
        map.putIfAbsent(key, value);
        return map.get(key);
    }
}
