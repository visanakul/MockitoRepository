package com.mockito.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mockito.pojo.Employee;
import com.mockito.pojo.EmployeeList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestControllerAdvice
@RestController
@RequestMapping("/emp")
@Api(value = "Employee Resource", description = "All employee related operations")
public class MyEmployeeService {
	@Autowired(required = true)
	private EmployeeList employeeList;

	@ApiOperation(value = "Search employee by ID", response = Employee.class)
	@GetMapping(value = "/find", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Employee getEmployeeData(@RequestParam("id") Integer id) {
		Optional<Employee> empOptional = employeeList.getEmpList().stream().filter(e -> {
			return e.getId() == id;
		}).findFirst();

		return empOptional.get();

	}

	@ApiOperation(value = "Method returns Employee List", response = List.class)
	@GetMapping(value = "/findAll", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public EmployeeList getAllEmployeeData() {
		return employeeList;
	}

}
