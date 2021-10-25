package com.dbit.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class City extends AbstractEntity {
    private String name;
    private List<Department> departments = new ArrayList<>();

    public City withId(Integer id) {
        setId(id);
        return this;
    }


    public City withName(String name) {
        setName(name);
        return this;
    }

    public City addDepartment(Department department) {
        if (department != null) {
            departments.add(department);
        }
        return this;
    }

}
