/*
 * Author Name:
 * Date: 11/28/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.service.impl;

import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;
import com.exam.example.examportalproject.repository.RoleRepository;
import com.exam.example.examportalproject.repository.UserRepository;
import com.exam.example.examportalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
    this.roleRepository = roleRepository;
}

    private final RoleRepository roleRepository;


//creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

       User local =this.userRepository.findByuserName(user.getUserName());
       if(local!=null){
           System.out.println("User is already there !!");
           throw new Exception("User already present !!");
       }else {
           //user create

           for(UserRole userRole:userRoles)
           {
               roleRepository.save(userRole.getRole());
           }
           user.getUserRoles().addAll(userRoles);
           local = this.userRepository.save(user);

       }
        return local;
    }
}
