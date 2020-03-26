package com.hello.controller;

import com.hello.model.Role;
import com.hello.model.RoleType;
import com.hello.model.User;
import com.hello.service.ServiceRole;
import com.hello.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceRole serviceRole;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", "true");
        }
        return "/templates/signIn";
    }

    @RequestMapping("/registration")
    public String registration() {
        return "/templates/signUp";
    }

    @PostMapping("/registration")
    public String addUserRegistration(String name, String password, String age, String city) {
        boolean addOrNot = false;
        try {
            int ageInt = Integer.parseInt(age);
            Role role = serviceRole.getRoleWithName(RoleType.ROLE_USER.name());
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            User user = new User(name, ageInt, password, city, roles);
            addOrNot = serviceUser.addUserService(user);
        } catch (NumberFormatException e) {

        }

        String redirect = null;
        if (addOrNot) {
            redirect = "redirect:/users";
        } else {
            redirect = "redirect:/registration";
        }
        return redirect;
    }
}

