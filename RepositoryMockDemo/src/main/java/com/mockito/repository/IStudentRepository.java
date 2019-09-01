package com.mockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mockito.entity.StudentEntity;

public interface IStudentRepository extends JpaRepository<StudentEntity, Integer> {
	
}
