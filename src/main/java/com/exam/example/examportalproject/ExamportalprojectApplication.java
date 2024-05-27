package com.exam.example.examportalproject;

import com.exam.example.examportalproject.model.Role;
import com.exam.example.examportalproject.model.User;
import com.exam.example.examportalproject.model.UserRole;
import com.exam.example.examportalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ExamportalprojectApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalprojectApplication.class, args);
		System.out.println("hello exam portal see here ");
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFirstName("Sanjay");
//		user.setLastName("Rawat");
//		user.setUsername("admin@123");
//		user.setPassword(this.bCryptPasswordEncoder.encode("admin@12345"));
//		user.setEmail("admin123@gmail.com");
//		Role rol1 = new Role();
//		rol1.setRoleId(55);
//		rol1.setRoleName("Admin");
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(rol1);
//		userRole.setUser(user);
//
//		userRoleSet.add(userRole);
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println("user1.getUsername");
	}

}




