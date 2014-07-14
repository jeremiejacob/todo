package com.roo.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

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

	@NotNull
	private Date startTime;

	@NotNull
	private Date endTime;
	
	private Category category;

	@NotEmpty
	@Length(max = 50)
	private String description;
	
	public Task toEntity() {
		Task task = new Task();
		task.setId(id);
		task.setStartTime(startTime);
		task.setEndTime(endTime);
		task.setDescription(description);
		task.setCategory(category);
		return task;
	}
	
	public static TaskEditForm fromEntity(Task task) {
		TaskEditForm form  = new TaskEditForm();
		form.id = task.getId();
		form.startTime = task.getStartTime();
		form.endTime = task.getEndTime();
		form.category = task.getCategory();
		form.description = task.getDescription();
		return form;
	}
}
