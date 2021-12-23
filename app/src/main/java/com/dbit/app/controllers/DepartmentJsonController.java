package com.dbit.app.controllers;

import com.dbit.app.repositories.DepartmentRepository;
import com.dbit.app.repositories.EmployeeRepository;
import com.dbit.model.Department;
import com.dbit.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

//@WebServlet("/api/departments")
@Controller
public class DepartmentJsonController {
    static final String DEPARTMENT_REPOSITORY_PREFIX = "departmentRepository";
    private static final String ID = "id";
    private DepartmentRepository repository;
    private final Map<String, DepartmentRepository> repositoryMap;
    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    public DepartmentJsonController(Map<String, DepartmentRepository> repositoryMap) {
        this.repositoryMap = repositoryMap;
    }

    @PostConstruct
    public void init()  {
        repository = repositoryMap.get(DEPARTMENT_REPOSITORY_PREFIX + capitalize(repositoryType));
    }

    @RequestMapping(path = "/api/departments", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Department> getAll() {
        return repository.findAll();
    }

    @RequestMapping(path = "/api/departments/{id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Department getDepartment(@PathVariable int id) {
        Optional<Department> department = repository.find(id);
        if (department.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id: " + id);
        }
        return department.get();
    }

    @RequestMapping(path = "/api/departments", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Department createDepartment(@RequestBody Department department) {
        return repository.save(department);
    }

    @RequestMapping(path = "/api/departments", produces = "application/json", method = RequestMethod.PUT)
    @ResponseBody
    public Department updateDepartment(@RequestBody Department department) {
        return repository.save(department);
    }



    @RequestMapping(path = "/api/departments/{id}", produces = "application/json", method = RequestMethod.DELETE)
    @ResponseBody
    protected Department deleteDepartment(@PathVariable int id) {
        Optional<Department> department = repository.find(id);
        if (department.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id: " + id);
        }
        Optional<Department> removed = repository.remove(department.get());
        if (removed.isPresent()) {
            return department.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id: " + id);
        }
    }
}
