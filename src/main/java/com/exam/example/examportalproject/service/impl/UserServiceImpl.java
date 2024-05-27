/*
 * Author Name:
 * Date: 11/28/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

//import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.exception.UserAlreadyExistsException;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;
import com.exam.example.examportalproject.repository.RoleRepository;
import com.exam.example.examportalproject.repository.UserRepository;
import com.exam.example.examportalproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private final RoleRepository roleRepository;

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistsException {
        User local = this.userRepository.findByusername(user.getUsername());

        if (local != null) {
            logger.error("User already present: {}", user.getUsername());
            throw new UserAlreadyExistsException("User already present with username: " + user.getUsername());
        } else {
            // Save roles
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }

            // Set roles to user
            user.getUserRoles().addAll(userRoles);

            // Save user
            local = this.userRepository.save(user);
            logger.info("User created successfully: {}", user.getUsername());
        }

        return local;
    }

    @Override
    public User fatchUserByusername(String username) {
        return this.userRepository.findByusername(username);
    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean checkUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

}

