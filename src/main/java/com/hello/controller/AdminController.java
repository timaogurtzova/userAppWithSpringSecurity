package com.hello.controller;

import com.hello.model.Role;
import com.hello.model.RoleType;
import com.hello.model.User;
import com.hello.service.ServiceRole;
import com.hello.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ServiceUser serviceUser;

    private ServiceRole serviceRole;

    @Autowired
    public void setUserService(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Autowired
    public void setRoleService(ServiceRole serviceRole) {
        this.serviceRole = serviceRole;
    }

    @GetMapping
    public ModelAndView getPageAdmin() {
        List<User> users = serviceUser.getAllUserService();
        Set<Role> roles = serviceRole.getAllRolesService();
        Map<String, Collection<?>> models = new HashMap<>();
        models.put("users", users);
        models.put("roles", roles);
        return new ModelAndView("/admin/adminpage").addAllObjects(models);
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id") int id) {
        serviceUser.deleteUserById(id);
        return getPageAdmin();
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable(name = "id") int id) {
        User user = serviceUser.getUserByIdService(id);
        Set<Role> roles = serviceRole.getAllRolesService();
        return new ModelAndView("/admin/update", "user", user)
                .addObject("roles", roles);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(@RequestParam(value = "id") String idStr,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "age") String ageStr,
                             @RequestParam(value = "city") String city,
                             @RequestParam(value = "roleArray") String[] roleArray) {
        long id = Long.parseLong(idStr);
        int age = Integer.parseInt(ageStr);
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < roleArray.length; i++) {
            switch (roleArray[i]) {
                case "ROLE_USER": {
                    roles.add(new Role(2L, RoleType.valueOf(roleArray[i])));
                    break;
                }
                case "ROLE_ADMIN": {
                    roles.add(new Role(1L, RoleType.valueOf(roleArray[i])));
                    break;
                }
            }
        }
        User user = new User(id, name, age, password, city, roles);
        serviceUser.updateUserService(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserPost(@RequestParam(value = "name") String name,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "age") String ageStr,
                              @RequestParam(value = "city") String city,
                              @RequestParam(value = "roleArray") String[] roleArray) {
        int age = Integer.parseInt(ageStr);
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < roleArray.length; i++) {
            switch (roleArray[i]) {
                case "ROLE_USER": {
                    roles.add(new Role(2L, RoleType.valueOf(roleArray[i])));
                    break;
                }
                case "ROLE_ADMIN": {
                    roles.add(new Role(1L, RoleType.valueOf(roleArray[i])));
                    break;
                }
            }

        }
        User user = new User(name, age, password, city, roles);
        serviceUser.addUserService(user);
        return "redirect:/admin";
    }


}
