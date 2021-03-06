package com.roo.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.User;

@RooJavaBean
public class UserEditForm {
	
	
	private Integer id;
	
	@NotEmpty
	@Length(max = 50, min = 4)
	private String email;
	
	@NotEmpty
	@Length(max = 50, min = 4)
	private String password;
	
	/**
     * Generate Users entity object from this instance values.
     */
	
	public User toEntity() {
		User users = new User();
		users.setId(id);
		users.setEmail(email);
		users.setPassword(password);
		return users;
	}
	
	public static UserEditForm fromEntity(User users) {
		UserEditForm form = new UserEditForm();
		form.setId(users.getId());
		form.setEmail(users.getEmail());
		form.setPassword(users.getPassword());
		return form;
	}
}
