package com.maksymmykytiuk.elephants.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.people.Lecturer;
import com.maksymmykytiuk.elephants.model.user.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "comment_generator")
    @SequenceGenerator(
            name = "comment_generator",
            sequenceName = "comment_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected Subject subject;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    protected User from;

    @Column(name = "content")
    @NotEmpty
    @Size(max = 512)
    private String content;
}
