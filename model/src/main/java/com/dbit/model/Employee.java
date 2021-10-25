package com.dbit.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends AbstractEntity {
    private String name;
    private Title title;
    private int salary;
//    @JsonManagedReference
    private Set<Department> departments = new HashSet<>();

    public Employee withId(Integer id) {
        setId(id);
        return this;
    }



    public Employee withName(String name) {
        setName(name);
        return this;
    }

    public Employee withSalary(Integer salary) {
        setSalary(salary);
        return this;
    }

    public Employee withTitle(Title title) {
        setTitle(title);
        return this;
    }

    public Employee addDepartment(Department department) {
        if (department != null) {
            departments.add(department);
        }
        return this;
    }

}
