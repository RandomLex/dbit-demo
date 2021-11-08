package com.dbit.model.hierarchy.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "animal_generator")
    @TableGenerator(
            name = "animal_generator",
            table = "sequence_table",
            pkColumnName = "name",
            pkColumnValue = "animals",
            valueColumnName = "last_id",
            initialValue = 0,
            allocationSize = 50
    )
    private long id;
    private String origin;
}
