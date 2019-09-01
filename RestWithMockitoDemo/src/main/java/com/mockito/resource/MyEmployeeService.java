package com.mockito.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockito.pojo.Employee;

@RestController
@RequestMapping("/emp")
public class MyEmployeeService {
	private List<Employee> empList;

	public MyEmployeeService() {
		empList = new ArrayList<>();
		empList.add(new Employee(1, "emp1", 1_20_000));
		empList.add(new Employee(2, "emp2", 2_20_000));
		empList.add(new Employee(3, "emp3", 1_80_000));
	}

	@GetMapping("/find")
	public Employee getEmployeeData(@RequestParam("id") Integer id) {
		Optional<Employee> empOptional = empList.stream().filter(e -> {
			return e.getId() == id;
		}).findFirst();

		return empOptional.get();

	}

	@GetMapping("/findAll")
	public List<Employee> getAllEmployeeData() {
		return empList;
	}

}
