package com.hello.controller;

import com.hello.model.Role;
import com.hello.model.RoleType;
import com.hello.model.User;
import com.hello.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ServiceUser serviceUser;

    @Autowired
    public void setUserService(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping
    public ModelAndView getPageAdmin() {
        List<User> users = serviceUser.getAllUserService();
        return new ModelAndView("/admin/adminpage", "users", users);
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") int id) {
        serviceUser.deleteUserById(id);
        return getPageAdmin();
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") int id) {
        User user = serviceUser.getUserByIdService(id);
        return new ModelAndView("update", "user", user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(@RequestParam(value = "id") String idStr,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "age") String ageStr,
                             @RequestParam(value = "city") String city,
                             @RequestParam(value = "role") String role) {
        long id = Long.parseLong(idStr);
        int age = Integer.parseInt(ageStr);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(RoleType.USER));
        User user = new User(id, name, age, password, city, roles);
        serviceUser.updateUserService(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserPost(@RequestParam(value = "name") String name,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "age") String ageStr,
                              @RequestParam(value = "city") String city,
                              @RequestParam(value = "role") String role) {
        int age = Integer.parseInt(ageStr);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(RoleType.USER));
        User user = new User(name, age, password, city, roles);
        serviceUser.addUserService(user);
        return "redirect:/admin";
    }


}
