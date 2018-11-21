package com.maksymmykytiuk.elephants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Entity
@Table(name = "department")
@Data
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(generator = "department_generator")
    @SequenceGenerator(
            name = "department_generator",
            sequenceName = "department_sequence"
    )
    private Long id;

    @Column(name = "department")
    @NotEmpty
    @Size(max = 100)
    private String department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = CASCADE)
    @JsonIgnore
    private Faculty faculty;
}
