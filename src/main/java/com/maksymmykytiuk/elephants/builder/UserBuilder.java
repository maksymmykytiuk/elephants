package com.maksymmykytiuk.elephants.builder;

import com.maksymmykytiuk.elephants.model.user.Role;
import com.maksymmykytiuk.elephants.model.user.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserBuilder {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean active;
    private Set<Role> roles;
    private Long user;
    private Long userType;

    public UserBuilder() {
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder active(Boolean active) {
        this.active = active;
        return this;
    }

    public UserBuilder role(Role role) {
        this.roles = new HashSet(Collections.singleton(role));
        return this;
    }

    public UserBuilder roles(List<Role> roles) {
        this.roles = new HashSet<>(roles);
        return this;
    }

    public UserBuilder user(Long user) {
        this.user = user;
        return this;
    }

    public UserBuilder userType(Long userType) {
        this.userType = userType;
        return this;
    }

    public User build() {
        User newUser = new User();
        newUser.setId(id);
        newUser.setActive(active);
        newUser.setPassword(password);
        newUser.setPhone(phone);
        newUser.setActive(active);
        newUser.setRoles(roles);
        newUser.setEmail(email);
        newUser.setUser(user);
        newUser.setUsername(username);
        newUser.setUserType(userType);
        return newUser;
    }
}
