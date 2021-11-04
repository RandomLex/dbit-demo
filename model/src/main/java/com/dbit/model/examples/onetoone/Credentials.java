package com.dbit.model.examples.onetoone;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@ToString(exclude = "person")
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
public class Credentials {
    @Id
    @Column(name = "person_id")
    private long id;

    private String login;
    private String password;

    @OneToOne
    @JoinColumn(name = "person_id")
    @MapsId
    private Person person;
}
