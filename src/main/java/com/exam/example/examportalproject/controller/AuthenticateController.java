package com.exam.example.examportalproject.controller;

import com.exam.example.examportalproject.config.JwtUtils;
import com.exam.example.examportalproject.model.JwtRequest;
import com.exam.example.examportalproject.model.JwtResponse;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    // Generate token
    @PostMapping(value = "/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            ApiResponse<User> errorResponse = new ApiResponse<>("error", e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled: " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("The username or password you provided is incorrect.");
        }
    }

    // Returns the details of current user
    @GetMapping(value = "/current-user")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = (User) this.userDetailsService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
