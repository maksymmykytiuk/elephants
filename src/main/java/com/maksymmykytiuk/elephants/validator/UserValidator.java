package com.maksymmykytiuk.elephants.validator;

import com.maksymmykytiuk.elephants.model.user.User;
import com.maksymmykytiuk.elephants.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if (user.getUsername().length() < 4 || user.getUsername().length() > 32)
            errors.rejectValue("username", "size.user.username");

        if (userService.findUserByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "duplicate.user.username");

        if (userService.findUserByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "duplicate.user.email");

        if (user.getPassword().length() < 4)
            errors.rejectValue("password", "size.user.password");
    }
}