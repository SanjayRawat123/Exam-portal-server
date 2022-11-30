package com.exam.example.examportalproject.repository;

import com.exam.example.examportalproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
