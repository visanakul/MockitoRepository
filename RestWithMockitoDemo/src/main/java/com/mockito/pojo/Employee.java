package com.mockito.pojo;

import lombok.Data;

@Data
public class Employee {
	private int id;
	private String name;
	private int sal;
	public Employee(int id, String name, int sal) {
		this.id=id;
		this.name=name;
		this.sal=sal;
	}

}
