package com.tacocloud.tacos.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.type.TrueFalseType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

	@Id
	@Column(length = 64)
	private String id;
	
	private String name;
	
//	@Enumerated(EnumType.STRING) // Use Converter instead -> WRONG: không liên quan đến Converter!!!!!
	@Enumerated(EnumType.STRING)
	private Type type;
	
	
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE 
	}
}
