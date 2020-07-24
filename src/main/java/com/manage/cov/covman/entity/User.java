package com.manage.cov.covman.entity;

import com.manage.cov.covman.utils.RoleEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Data
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	@NotEmpty
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	@NotEmpty
	private String lastName;

	@Column(name = "EMAIL")
	@NotEmpty
	private String email;

	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;

	@Column(name = "ROLE")
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	@Embedded
	private Address address;
}
