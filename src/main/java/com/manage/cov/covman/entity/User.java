package com.manage.cov.covman.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@MappedSuperclass
@Data
public class User implements Serializable {

	@Column(name = "FIRST_NAME", nullable = false)
	@NotEmpty
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	@NotEmpty
	private String lastName;

	@Embedded
	private Address address;
}
