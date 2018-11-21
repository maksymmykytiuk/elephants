package com.maksymmykytiuk.elephants.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculty")
@Data
public class Faculty {

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(generator = "faculty_generator")
    @SequenceGenerator(
            name = "faculty_generator",
            sequenceName = "faculty_sequence"
    )
    private Long id;

    @Column(name = "faculty")
    @NotEmpty
    @Size(max = 100)
    private String faculty;
}
