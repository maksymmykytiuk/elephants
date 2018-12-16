package com.maksymmykytiuk.elephants.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(
            name = "user_generator",
            sequenceName = "user_sequence"
    )
    private Long id;

    @Column(name = "username", unique = true)
    @NotEmpty
    @Size(max = 64)
    private String username;

    @Column(name = "password")
    @NotEmpty
    @Size(max = 64)
    private String password;

    @Column(name = "email")
    @NotEmpty
    @Size(max = 128)
    private String email;

    @Column(name = "phone")
    @NotEmpty
    @Size(max = 13)
    private String phone;

    @Column(name = "active")
    @NotEmpty
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles;

    @Column(name = "people")
    @NotEmpty
    private Long user;

    @Column(name = "user_type_id")
    @NotEmpty
    private Long userType;
}
