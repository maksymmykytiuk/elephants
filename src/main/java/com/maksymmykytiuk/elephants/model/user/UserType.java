package com.maksymmykytiuk.elephants.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_type")
@Data
public class UserType {

    @Id
    @Column(name = "user_type_id")
    @GeneratedValue(generator = "user_type_generator")
    @SequenceGenerator(
            name = "user_type_generator",
            sequenceName = "user_type_sequence"
    )
    private Long id;

    @Column(name = "user_type")
    @NotEmpty
    @Size(max = 64)
    private String userType;
}
