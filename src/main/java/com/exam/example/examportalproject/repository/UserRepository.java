package com.exam.example.examportalproject.repository;

import com.exam.example.examportalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
 public User findByusername(String username);

 boolean existsByUsername(String username);
}
