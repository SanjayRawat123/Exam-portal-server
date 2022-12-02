/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.model.Role;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;
import com.exam.example.examportalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    private UserService userService;

    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/")
    public User saveUserFun(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user,roles);



    }

    @GetMapping(value = "/{userName}")
public User getUser(@PathVariable("userName") String userName)    {
        return this.userService.fatchUserByUserName(userName);
    }

    @DeleteMapping(value = "/{id}")
    public void deletfun1(@PathVariable("id") long id){
        this.userService.deleteUser(id);

    }

}
