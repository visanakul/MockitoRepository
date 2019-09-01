package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mockito.entity.StudentEntity;
import com.mockito.repository.IStudentRepository;
import com.mockito.service.StudentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RepositoryMockDemoApplicationTests {

	@InjectMocks
	private StudentServiceImpl service;

	@Mock
	private IStudentRepository repository;

	private static List<StudentEntity> list;

	@BeforeClass
	public static void init() {
		list = Stream.of(new StudentEntity(1, "Std1", 95.3f), new StudentEntity(2, "Std2", 99.4f),
				new StudentEntity(3, "Std3", 88.3f)).collect(Collectors.toList());
	}

	@Test
	public void testFindByRoll() {
		int roll = 1;
		System.out.println();
		when(repository.findById(roll)).thenReturn(Optional.of(list.get(0)));
		assertEquals(roll, service.getByRoll(roll).getRoll().intValue());
	}

	@Test
	public void testFindAll() {
		when(repository.findAll()).thenReturn(list);
		assertEquals(3, service.getAllStudents().size());
	}

	@Test
	public void testDeleteByRollRecord() {
		int roll = 1;
		service.deleteByRoll(roll);
		verify(repository, times(1)).deleteById(roll);
	}
}
