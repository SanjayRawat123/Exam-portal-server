package com.exam.example.examportalproject;

import com.exam.example.examportalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ExamportalprojectApplication implements CommandLineRunner {
	@Autowired
private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(ExamportalprojectApplication.class, args);

		System.out.println("hello exam portal");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");
/**
		User user = new User();
		user.setFirstName("ram");
		user.setLastName("rama");
		user.setUserName("durgesh");
		user.setPassword("abc@123");
		user.setProfile("default.png");
		user.setEmail("rawat123@gmail.com");
		user.setPhone("123456789");


		Role role1 = new Role();

		role1.setRoleId(44l);

		role1.setRoleName("ADMIN");

		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);

		userRoleSet.add(userRole);


		User user1 = this.userService.createUser(user,userRoleSet);
		System.out.println();



     */}
}




