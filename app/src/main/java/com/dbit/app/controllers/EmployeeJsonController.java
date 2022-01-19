package com.dbit.app.controllers;

import com.dbit.app.repositories.EmployeeRepository;
import com.dbit.app.services.EmployeeService;
import com.dbit.model.dbit.Employee;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/api/employees", produces = "application/json")
@RequiredArgsConstructor
public class EmployeeJsonController {

    private final EmployeeService employeeService;
//    static final String EMPLOYEE_REPOSITORY_PREFIX = "employeeRepository";
//
//    private final Map<String, EmployeeRepository> repositoryMap;
//    @Value("${repository.type}")
//    private String repositoryType;
//
//    private EmployeeRepository repository;
//
//    @Autowired
//    public EmployeeJsonController(Map<String, EmployeeRepository> repositoryMap) {
//        this.repositoryMap = repositoryMap;
//    }
//
//    @PostConstruct
//    public void init()  {
//        repository = repositoryMap.get(EMPLOYEE_REPOSITORY_PREFIX + capitalize(repositoryType));
//    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        return ResponseEntity.of(employeeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee employee) {
        if (employee != null && id != employee.getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Employee id must be equal with id in path: " + id + " != " + employee.getId());
        }
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id) {
        Optional<Employee> employee = employeeService.get(id);
        if (employee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(employeeService.delete(employee.get()));
    }

//    private static final String ID = "id";
//    private EmployeeRepository repository;
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        repository = ctx.getBean(EMPLOYEE_REPOSITORY_PREFIX + type, EmployeeRepository.class);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String id = req.getParameter(ID);
//        writeEntityToBody(id == null
//                ? repository.findAll()
//                : repository.find(Integer.parseInt(id)), resp);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Employee employee = toEntity(Employee.class, req);
//        writeEntityToBody(repository.save(employee), resp);
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Employee employee = toEntity(Employee.class, req);
//        writeEntityToBody(repository.save(employee), resp);
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Employee employee = toEntity(Employee.class, req);
//        writeEntityToBody(repository.remove(employee), resp);
//    }
}
