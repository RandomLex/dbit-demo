package com.dbit.app.controllers;

import com.dbit.app.repositories.EmployeeRepository;
import com.dbit.app.repositories.RepositoryFactory;
import com.dbit.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/api/employees")
public class EmployeeJsonController extends CommonJsonController {
    private static final String ID = "id";
    private EmployeeRepository repository = RepositoryFactory.getEmployeeRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID);
        writeEntityToBody(id == null
                ? repository.findAll()
                : repository.find(Integer.parseInt(id)), resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = toEntity(Employee.class, req);
        writeEntityToBody(repository.save(employee), resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = toEntity(Employee.class, req);
        writeEntityToBody(repository.save(employee), resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = toEntity(Employee.class, req);
        writeEntityToBody(repository.remove(employee), resp);
    }
}
