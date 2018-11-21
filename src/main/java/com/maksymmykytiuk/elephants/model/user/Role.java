package com.maksymmykytiuk.elephants.model.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "role_generator")
    @SequenceGenerator(
            name = "role_generator",
            sequenceName = "role_sequence"
    )
    private Long id;

    @Column(name = "role")
    @NotEmpty
    @Size(max = 64)
    private String role;
}
