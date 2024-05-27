/*
 * Author Name:
 * Date: 12/14/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.config.JwtUtils;
import com.exam.example.examportalproject.model.JwtRequset;
import com.exam.example.examportalproject.model.JwtResponse;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")

public class AuthenticateContriller {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    //generate token
    @PostMapping(value = "/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequset jwtRequset) throws Exception {

        try{
            System.out.println("checking user request body ");
            System.out.println(jwtRequset.getUsername());
            authenticate(jwtRequset.getUsername(),jwtRequset.getPassword());
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not Found");
        }
        //////authentivcate

        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequset.getUsername());

      String token=  this.jwtUtils.generateToken(userDetails);
      return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username,String password) throws Exception{

        try {
            System.out.println(username);
            System.out.println(password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (DisabledException e){
            throw new Exception("user Disabled"+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalied Credential" +e.getMessage());
        }
    }
    //returns the detaile of current user
    @GetMapping(value = "/current-user")
    public User getCurrentUser(Principal principal){
        System.out.println("current user called ");
        return ((User)this.userDetailsService.loadUserByUsername(principal.getName())) ;
    }
}
