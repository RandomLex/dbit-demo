package com.dbit.model.examples;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String model;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

//    @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Embedded
    @PrimaryKeyJoinColumn
    private AudioSystem audioSystem;
}
