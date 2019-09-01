package com.mockito.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "employee")
@ApiModel(value = "Employee Model",description = "Stores employee information")
public class Employee {
	@ApiModelProperty(value = "Employee Id",notes = "Used to store id of employee")
	private int id;
	@ApiModelProperty(value = "Employee name",notes = "Used to store name of employee")
	private String name;
	@ApiModelProperty(value = "Employee salary",notes = "Used to store salary of employee")
	private int sal;

	public Employee(int id, String name, int sal) {
		this.id = id;
		this.name = name;
		this.sal = sal;
	}

}
