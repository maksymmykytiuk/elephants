package com.maksymmykytiuk.elephants.model.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.Department;
import com.maksymmykytiuk.elephants.model.Position;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lecturer")
@Getter
@Setter
@NoArgsConstructor
public class Lecturer {

    @Id
    @Column(name = "lecturer_id")
    @GeneratedValue(generator = "lecture_generator")
    @SequenceGenerator(
            name = "lecturer_generator",
            sequenceName = "lecturer_sequence"
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

    @Column(name = "employee_id")
    @NotEmpty
    @Size(min = 10, max = 10)
    private String employeeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Department department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "position_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Position position;
}
