package com.maksymmykytiuk.elephants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculty_group")
@Data
public class Group {

    @Id
    @Column(name = "faculty_group_id")
    @GeneratedValue(generator = "faculty_group_generator")
    @SequenceGenerator(
            name = "faculty_group_generator",
            sequenceName = "faculty_group_sequence"
    )
    private Long id;

    @Column(name = "group_name")
    @NotEmpty
    @Size(max = 100)
    private String group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Department department;
}
