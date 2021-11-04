package com.dbit.model.examples;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@NoArgsConstructor
@SuperBuilder
//@IdClass(ReportKey.class)
@Entity
public class Report {

    @EmbeddedId
    private ReportKey id;

//    @Id
//    private String name;
//    @Id
//    private String type;

    private String recipient;

}
