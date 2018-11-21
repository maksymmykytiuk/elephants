package com.maksymmykytiuk.elephants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.people.Lecturer;
import com.maksymmykytiuk.elephants.model.people.Student;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "subject")
@Data
public class Subject {

    @Id
    @Column(name = "subject_id")
    @GeneratedValue(generator = "subject_generator")
    @SequenceGenerator(
            name = "subject_generator",
            sequenceName = "subject_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Lecturer author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "approved_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Lecturer approved;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Column(name = "approve_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveDate;

    @Column(name = "subject")
    @NotEmpty
    private String subject;

    @Column(name = "status")
    @NotEmpty
    private Long status;

    @Column(name = "type")
    @NotEmpty
    private Long type;
}
