package com.mockito.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "std")
public class StudentEntity {

	@Id
	private Integer roll;
	private String name;
	private Float per;

	public StudentEntity() {
	}

	public StudentEntity(Integer roll, String name, Float per) {
		super();
		this.roll = roll;
		this.name = name;
		this.per = per;
	}
}
