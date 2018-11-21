package com.maksymmykytiuk.elephants.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maksymmykytiuk.elephants.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
@Data
public class Chat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(generator = "chat_generator")
    @SequenceGenerator(
            name = "chat_generator",
            sequenceName = "chat_sequence"
    )
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_user", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users;
}
