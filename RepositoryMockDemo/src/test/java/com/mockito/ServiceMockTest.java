package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mockito.entity.StudentEntity;
import com.mockito.model.StudentModel;
import com.mockito.repository.IStudentRepository;
import com.mockito.service.StudentServiceImpl;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceMockTest {

	@InjectMocks
	@Autowired
	private StudentServiceImpl service;
	@MockBean
	private IStudentRepository repository;

	@Test
	public void testSize() {
		when(repository.count()).thenReturn(5L);
		assertEquals(5, service.countTotalRecords().longValue());
	}

	@Test
	public void testFindByIdSuccess() {
		when(repository.findById(1)).thenReturn(Optional.of(new StudentEntity(1, "std1", 98.34f)));
		assertEquals(1, service.getByRoll(1).getRoll().intValue());
	}

	@Test(expected = RuntimeException.class)
	public void testFindByIdFail() {
		when(repository.findById(11)).thenReturn(null);
		assertEquals(null, service.getByRoll(1));
	}

	@Test
	public void testSave() {
		StudentEntity beforeStudentEntity = new StudentEntity(null, "std1", 98.34f);
		StudentEntity afterStudentEntity = new StudentEntity(1, "std1", 98.34f);
		StudentModel beforeStudentModel = new StudentModel();
		BeanUtils.copyProperties(beforeStudentEntity, beforeStudentModel);
		when(repository.save(beforeStudentEntity)).thenReturn(afterStudentEntity);
		assertEquals(1, service.addStudent(beforeStudentModel).getRoll().intValue());
	}

	@Test
	public void testDelete() {
		service.deleteByRoll(1);
		verify(repository,times(1)).deleteById(1);
	}

	@Test
	public void testUpdate() {
		StudentEntity beforeStudentEntity = new StudentEntity(1, "std1", 98.34f);
		StudentEntity afterStudentEntity = new StudentEntity(1, "std1New", 98.54f);
		StudentModel beforeStudentModel = new StudentModel();
		BeanUtils.copyProperties(beforeStudentEntity, beforeStudentModel);
		when(repository.save(beforeStudentEntity)).thenReturn(afterStudentEntity);
		assertEquals("std1New", service.updateStudent(beforeStudentModel).getName());
	}
}
