package com.mockito.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockito.entity.StudentEntity;
import com.mockito.model.StudentModel;
import com.mockito.repository.IStudentRepository;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired(required = true)
	private IStudentRepository repository;

	
	@Override
	public StudentModel getByRoll(int roll) {
		Optional<StudentEntity> studentEntityOptional = repository.findById(roll);
		if (!studentEntityOptional.isPresent()) {
			throw new RuntimeException("No record found...");
		}
		StudentEntity studentEntity = studentEntityOptional.get();
		StudentModel studentModel = new StudentModel();
		BeanUtils.copyProperties(studentEntity, studentModel);
		return studentModel;
	}

	@Override
	public List<StudentModel> getAllStudents() {
		List<StudentEntity> studentEntities = repository.findAll();
		List<StudentModel> studentModels = new ArrayList<>(studentEntities.size());

		studentEntities.forEach(studentEntity -> {
			StudentModel studentModel = new StudentModel();
			BeanUtils.copyProperties(studentEntity, studentModel);
			studentModels.add(studentModel);
		});

		return studentModels;
	}

	@Override
	public void deleteByRoll(int roll) {
		repository.deleteById(roll);
	}

	@Override
	public StudentModel addStudent(StudentModel studentModel) {
		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(studentModel, studentEntity);
		studentEntity = repository.save(studentEntity);
		BeanUtils.copyProperties(studentEntity, studentModel);
		return studentModel;
	}

	@Override
	public Long countTotalRecords() {
		return repository.count();
	}

	@Override
	public StudentModel updateStudent(StudentModel studentModel) {
		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(studentModel, studentEntity);
		studentEntity = repository.save(studentEntity);
		BeanUtils.copyProperties(studentEntity, studentModel);
		return studentModel;
	}

}
