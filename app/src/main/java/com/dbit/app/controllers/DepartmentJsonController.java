package com.dbit.app.controllers;

import com.dbit.app.repositories.DepartmentRepository;
import com.dbit.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

@Controller
@RequestMapping(path = "/api/departments", produces = "application/json")
@ResponseBody
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

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Department> getAll() {

        return repository.findAll();
    }

//    @RequestMapping(path = "/api/departments/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable int id) {
        Optional<Department> department = repository.find(id);
        if (department.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id: " + id);
        }
        return department.get();
    }

//    @RequestMapping( method = RequestMethod.POST)
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return repository.save(department);
    }

//    @RequestMapping(method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public Department updateDepartment(
            @PathVariable int id,
            @RequestBody Department department) {
        if (department != null && id != department.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Department id must be equal with id in path: " + id + " != " + department.getId());
        }
        return repository.save(department);
    }

//    @RequestMapping(path = "/api/departments/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public Department deleteDepartment(@PathVariable int id) {
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
