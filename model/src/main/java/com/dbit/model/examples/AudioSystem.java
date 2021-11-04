package com.dbit.model.examples;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@SuperBuilder
@Embeddable
public class AudioSystem {
    @Column(name = "music_power")
    private Integer musicPower;
    @Column(name = "music_name")
    private String musicName;
    @Column(name = "speakers")
    private Integer speakers;
}
