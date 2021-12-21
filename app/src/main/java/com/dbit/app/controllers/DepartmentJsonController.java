package com.dbit.app.controllers;

import com.dbit.app.repositories.DepartmentRepository;
import com.dbit.model.Department;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/departments")
public class DepartmentJsonController extends CommonJsonController {
    static final String DEPARTMENT_REPOSITORY_PREFIX = "departmentRepository";
    private static final String ID = "id";
    private DepartmentRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = ctx.getBean(DEPARTMENT_REPOSITORY_PREFIX + type, DepartmentRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID);
        writeEntityToBody(id == null
                ? repository.findAll()
                : repository.find(Integer.parseInt(id)), resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = toEntity(Department.class, req);
        writeEntityToBody(repository.save(department), resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = toEntity(Department.class, req);
        writeEntityToBody(repository.save(department), resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = toEntity(Department.class, req);
        writeEntityToBody(repository.remove(department), resp);
    }
}
