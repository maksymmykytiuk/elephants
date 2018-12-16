package com.maksymmykytiuk.elephants.controller;

import com.maksymmykytiuk.elephants.builder.UserBuilder;
import com.maksymmykytiuk.elephants.constant.UserType;
import com.maksymmykytiuk.elephants.model.user.Role;
import com.maksymmykytiuk.elephants.model.user.User;
import com.maksymmykytiuk.elephants.service.role.RoleService;
import com.maksymmykytiuk.elephants.service.user.UserService;
import com.maksymmykytiuk.elephants.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private String loginError;

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    RoleService roleService;

    @GetMapping(value = "/createUser")
    public ModelAndView createUser(ModelAndView modelAndView) {
        modelAndView.setViewName("createUser");
        return modelAndView;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration(@RequestParam("role") String roleName) {
        Role role = roleService.findRoleByName(roleName);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", UserBuilder.builder()
                .role(role)
                .userType(UserType.byRoleId(role.getId()))
                .build());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(ModelAndView modelAndView,
                                     @Valid @ModelAttribute("user") User user,
                                     BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        }

        userService.save(user);

        modelAndView.setViewName("login");
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error) {
        if (error != null)
            model.addAttribute("error", loginError);

        return "login";
    }
}