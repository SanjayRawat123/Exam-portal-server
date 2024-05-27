/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;


import com.exam.example.examportalproject.exception.UserAlreadyExistsException;
import com.exam.example.examportalproject.model.Role;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;
import com.exam.example.examportalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping(value = "/")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        try {
            user.setProfile("default.png");

            // Encoding password with bcryptpasswordencoder
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

            Set<UserRole> roles = new HashSet<>();
            Role role = new Role();
            role.setRoleId(45L);
            role.setRoleName("Normal");

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            roles.add(userRole);

            User createdUser = this.userService.createUser(user, roles);

            // Create response
            ApiResponse<User> response = new ApiResponse<>("success", "User created successfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (UserAlreadyExistsException e) {
            // Handle custom exception
            ApiResponse<User> errorResponse = new ApiResponse<>("error", e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            // Create error response
            ApiResponse<User> errorResponse = new ApiResponse<>("error", e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{userName}")

    public User getUser(@PathVariable("userName") String username) {
        return this.userService.fatchUserByusername(username);
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean exists = userService.checkUsername(username);
        return ResponseEntity.ok(exists);
    }

    @DeleteMapping(value = "/{id}")
    public void deletfun1(@PathVariable("id") long id) {
        this.userService.deleteUser(id);

    }





}
