package com.dbit.model.examples;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
@Embeddable
public class ReportKey implements Serializable {
    private String name;
    private String type;
}
