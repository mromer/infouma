package com.mromero.infoshool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	@Column(length=48)	
	@Size(max=48)
	private String description;
	
	@Column(length=48)	
	@Size(max=48)
	private String direccion;
	
	@Column(length=48)	
	@Size(max=48)
	private String telefono;	
	
	@Column(length=48)	
	@Size(max=48)
	private String horario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
