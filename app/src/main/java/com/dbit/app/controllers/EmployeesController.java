package com.dbit.app.controllers;

import com.dbit.app.repositories.EmployeeRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/employees")
public class EmployeesController extends AbstractController {
    static final String EMPLOYEE_REPOSITORY_PREFIX = "employeeRepository";

    private EmployeeRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = ctx.getBean(EMPLOYEE_REPOSITORY_PREFIX + type, EmployeeRepository.class);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employees", repository.findAll());
        req.getRequestDispatcher("employees.jsp").forward(req, resp);
    }
}
