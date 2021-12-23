package com.dbit.app.controllers;

import com.dbit.app.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

@Controller
@PropertySource("classpath:app.properties")
public class EmployeesController {
    static final String EMPLOYEE_REPOSITORY_PREFIX = "employeeRepository";

    private EmployeeRepository repository;
    private final Map<String, EmployeeRepository> repositoryMap;
    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    public EmployeesController(Map<String, EmployeeRepository> repositoryMap) {
        this.repositoryMap = repositoryMap;
    }

    @PostConstruct
    public void init()  {
        repository = repositoryMap.get(EMPLOYEE_REPOSITORY_PREFIX + capitalize(repositoryType));
    }


    @RequestMapping(path = "employees", method = RequestMethod.GET)
    protected ModelAndView allEmployees()  {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employees", repository.findAll());
        modelAndView.setViewName("employees");
        return modelAndView;
    }
}
