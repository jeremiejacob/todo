package com.roo.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.Category;
import com.roo.todo.entity.Task;

@RooJavaBean
public class TaskEditForm {

	private static final Logger logger = Logger.getLogger(TaskEditForm.class);
	
	private Integer id;

	@NotEmpty
	private Date startTime;

	@NotEmpty
	private Date endTime;
	
	private String category;

	@NotEmpty
	@Length(max = 50)
	private String Description;
	
}
