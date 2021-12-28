package com.dbit.app.controllers;

import com.dbit.app.repositories.DepartmentRepository;
import com.dbit.model.dbit.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

@RestController
@RequestMapping(path = "/api/departments", produces = "application/json")
public class DepartmentJsonController {
    static final String DEPARTMENT_REPOSITORY_PREFIX = "departmentRepository";

    private final Map<String, DepartmentRepository> repositoryMap;
    @Value("${repository.type}")
    private String repositoryType;

    private DepartmentRepository repository;

    @Autowired
    public DepartmentJsonController(Map<String, DepartmentRepository> repositoryMap) {
        this.repositoryMap = repositoryMap;
    }

    @PostConstruct
    public void init()  {
        repository = repositoryMap.get(DEPARTMENT_REPOSITORY_PREFIX + capitalize(repositoryType));
    }

    @GetMapping
    public List<Department> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable int id) {
        return ResponseEntity.of(repository.find(id));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(repository.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable int id,
            @RequestBody Department department) {
        if (department != null && id != department.getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Department id must be equal with id in path: " + id + " != " + department.getId());
        }
        return ResponseEntity.ok(repository.save(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable int id) {
        Optional<Department> department = repository.find(id);
        if (department.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(repository.remove(department.get()));
    }
}
