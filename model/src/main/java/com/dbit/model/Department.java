package com.dbit.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department extends AbstractEntity {
    private String name;
    private List<City> cities = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    public Department withId(Integer id) {
        setId(id);
        return this;
    }


    public Department withName(String name) {
        setName(name);
        return this;
    }

}
