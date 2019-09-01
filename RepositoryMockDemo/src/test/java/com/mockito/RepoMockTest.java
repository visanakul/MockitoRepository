package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mockito.entity.StudentEntity;
import com.mockito.repository.IStudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoMockTest {
	@MockBean
	private IStudentRepository repository;

	@Test
	public void testSize() {
		when(repository.count()).thenReturn(5L);
		assertEquals(5, repository.count());
	}

	@Test
	public void testFindByIdSuccess() {
		when(repository.findById(1)).thenReturn(Optional.of(new StudentEntity(1, "std1", 98.34f)));
		assertEquals(1, repository.findById(1).get().getRoll().intValue());
	}

	@Test
	public void testFindByIdFail() {
		when(repository.findById(11)).thenReturn(null);
		assertEquals(Optional.empty(), repository.findById(1));
	}

	@Test
	public void testSave() {
		StudentEntity beforeStudentEntity = new StudentEntity(null, "std1", 98.34f);
		StudentEntity afterStudentEntity = new StudentEntity(1, "std1", 98.34f);
		when(repository.save(beforeStudentEntity)).thenReturn(afterStudentEntity);
		assertEquals(1, repository.save(beforeStudentEntity).getRoll().intValue());
	}

	@Test
	public void testDelete() {
		StudentEntity studentEntity = new StudentEntity(1, "std1", 98.34f);
		repository.delete(studentEntity);
		repository.delete(studentEntity);
		verify(repository, times(2)).delete(studentEntity);
	}
	
	@Test
	public void testUpdate() {
		StudentEntity beforeStudentEntity = new StudentEntity(1, "std1", 98.34f);
		StudentEntity afterStudentEntity = new StudentEntity(1, "std1New", 98.54f);
		when(repository.save(beforeStudentEntity)).thenReturn(afterStudentEntity);
		assertEquals("std1New", repository.save(beforeStudentEntity).getName());
	}
}
