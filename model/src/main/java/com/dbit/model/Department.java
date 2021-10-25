package com.dbit.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Department extends AbstractEntity {
    private String name;
    private City city;
//    @JsonBackReference
    private List<Employee> employees = new ArrayList<>();

    public Department withId(Integer id) {
        setId(id);
        return this;
    }


    public Department withName(String name) {
        setName(name);
        return this;
    }

    public Department withCity(City city) {
        setCity(city);
        return this;
    }

}
