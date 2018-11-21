package com.maksymmykytiuk.elephants.model.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(generator = "student_generator")
    @SequenceGenerator(
            name = "student_generator",
            sequenceName = "student_sequence"
    )
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    @Size(max = 64)
    private String firstName;

    @Column(name = "middle_name")
    @NotEmpty
    @Size(max = 64)
    private String middleName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(max = 64)
    private String lastName;

    @Column(name = "student_id_card")
    @NotEmpty
    @Size(min = 10, max = 10)
    private String studentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Group group;
}
