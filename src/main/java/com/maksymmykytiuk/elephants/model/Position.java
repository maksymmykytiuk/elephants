package com.maksymmykytiuk.elephants.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "position")
@Data
public class Position {

    @Id
    @Column(name = "position_id")
    @GeneratedValue(generator = "position_generator")
    @SequenceGenerator(
            name = "position_generator",
            sequenceName = "position_sequence"
    )
    private Long id;

    @Column(name = "position")
    @NotEmpty
    private String position;
}
