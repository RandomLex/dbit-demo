package com.dbit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {
    private Integer id;

    public AbstractEntity withId(Integer id) {
        setId(id);
        return this;
    }
}
