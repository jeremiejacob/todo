package com.roo.todo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", length = 50, unique = true)
	@NotNull
	private String email;

	@Column(name = "password")
	@NotNull
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;
	
}
