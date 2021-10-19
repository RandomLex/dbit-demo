package com.dbit.app.repositories;

import com.dbit.app.exceptions.DatabaseException;
import com.dbit.model.City;
import com.dbit.model.Department;
import com.dbit.model.Employee;
import com.dbit.model.Title;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
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
                int eId = rs.getInt("e_id");
                int dId = rs.getInt("d_id");
                int cId = rs.getInt("c_id");
                int tId = rs.getInt("t_id");

                titleMap.putIfAbsent(tId, new Title()
                        .withId(tId)
                        .withName(rs.getString("t_name")));

                cityMap.putIfAbsent(cId, new City()
                        .withId(cId)
                        .withName(rs.getString("c_name")));

                departmentMap.putIfAbsent(dId, new Department()
                        .withId(dId)
                        .withName(rs.getString("d_name")));

                employeeMap.putIfAbsent(eId,
                        new Employee()
                                .withId(eId)
                                .withName(rs.getString("e_name"))
                                .withSalary(rs.getInt("salary"))
                                .withTitle(titleMap.get(tId))
                                .addDepartment(departmentMap.get(dId)));

                cityMap.computeIfPresent(cId, (id, city) -> city.addDepartment(departmentMap.get(dId)));
                departmentMap.computeIfPresent(dId, (id, department) -> department.withCity(cityMap.get(cId)));
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
}
