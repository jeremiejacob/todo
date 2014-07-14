package com.roo.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class TaskEditForm {

	private Integer id;

	@NotEmpty
	private Date startTime;

	@NotEmpty
	private Date endTime;
	private String categories;

	@NotEmpty
	@Length(max = 50)
	private String Description;

}
