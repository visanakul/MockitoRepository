package com.mockito.model;

import lombok.Data;

@Data
public class StudentModel {
	public StudentModel() {
	}

	private Integer roll;
	private String name;
	private Float per;

	public StudentModel(Integer roll, String name, Float per) {
		super();
		this.roll = roll;
		this.name = name;
		this.per = per;
	}

}
