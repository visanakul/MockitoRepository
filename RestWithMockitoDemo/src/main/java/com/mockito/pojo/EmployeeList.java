package com.mockito.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.stereotype.Service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Service
@Data
@XmlRootElement(name = "employees")
@XmlSeeAlso(Employee.class)
@ApiModel(value = "EmployeeList Model", description = "Used to work with employee list")
public class EmployeeList {
	@ApiModelProperty(value = "Employee list", notes = "Used to store list of employee")
	private List<Employee> empList;

	public EmployeeList() {
		empList = new ArrayList<>();
		empList.add(new Employee(1, "emp1", 1_20_000));
		empList.add(new Employee(2, "emp2", 2_20_000));
		empList.add(new Employee(3, "emp3", 1_80_000));
	}

	public Employee getEmployee(int index) {
		return empList.get(index);
	}
}
