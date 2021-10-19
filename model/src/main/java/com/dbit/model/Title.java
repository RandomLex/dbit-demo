package com.dbit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Title extends AbstractEntity {
    private String name;

    public Title withId(Integer id) {
        setId(id);
        return this;
    }


    public Title withName(String name) {
        setName(name);
        return this;
    }

}
