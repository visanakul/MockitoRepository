package com.mockito.service;

import java.util.List;

import com.mockito.model.StudentModel;

public interface IStudentService {
	
	StudentModel getByRoll(int roll) ;
	List<StudentModel> getAllStudents();
	void deleteByRoll(int roll);
	StudentModel addStudent(StudentModel studentModel);
	StudentModel updateStudent(StudentModel studentModel);
	Long countTotalRecords();
}
