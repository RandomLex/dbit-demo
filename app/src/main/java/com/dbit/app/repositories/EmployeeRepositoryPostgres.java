package com.dbit.app.repositories;

import com.dbit.app.exceptions.DatabaseException;
import com.dbit.model.Employee;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EmployeeRepositoryPostgres implements EmployeeRepository {
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
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement("select * from employee");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Employee()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withSalary(rs.getInt("salary")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e);
        }
        return result;
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
