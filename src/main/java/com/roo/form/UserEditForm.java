package com.roo.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.Users;

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
	
	public Users toEntity() {
		Users users = new Users();
		users.setId(id);
		users.setEmail(email);
		users.setPassword(password);
		return users;
	}
	
	public static UserEditForm fromEntity(Users users) {
		UserEditForm form = new UserEditForm();
		form.setId(users.getId());
		form.setEmail(users.getEmail());
		form.setPassword(users.getPassword());
		return form;
	}
}
