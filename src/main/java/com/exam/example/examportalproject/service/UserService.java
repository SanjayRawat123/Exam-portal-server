package com.exam.example.examportalproject.service;

import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;

import java.util.Set;

public interface UserService {

    //creating user

    public User createUser(User user, Set<UserRole>userRoles) throws Exception;
}
