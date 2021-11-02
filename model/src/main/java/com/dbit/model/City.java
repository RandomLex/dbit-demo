package com.dbit.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString(callSuper = true, exclude = "departments")
@EqualsAndHashCode(callSuper = true, exclude = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class City extends AbstractEntity {
    private String name;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Department> departments = new LinkedHashSet<>();

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
