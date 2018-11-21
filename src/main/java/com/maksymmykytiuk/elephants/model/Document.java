package com.maksymmykytiuk.elephants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.people.Lecturer;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "document")
@Data
public class Document {

    @Id
    @Column(name = "document_id")
    @GeneratedValue(generator = "document_generator")
    @SequenceGenerator(
            name = "document_generator",
            sequenceName = "document_sequence"
    )
    private Long id;

    @Column(name = "type")
    @NotEmpty
    private Long type;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Column(name = "status")
    @NotEmpty
    private Long status;

    @Column(name = "path")
    @NotEmpty
    private String path;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Subject subject;
}
