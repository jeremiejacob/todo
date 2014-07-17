package com.roo.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.Category;
import com.roo.todo.entity.Task;

@RooJavaBean
public class TaskEditForm {

	private static final Logger logger = Logger.getLogger(TaskEditForm.class);
	
	private Integer id;

	@NotEmpty
	private String startTime;

	private String endTime;

	private Integer categoryId;
	
	private Boolean status;

	@NotEmpty
	@Length(max = 50)
	private String description;
	
	public Task toEntity() {
		
		Task task = new Task();
		task.setId(id);
		try {
			task.setStartTime(DateUtils.parseDate(startTime, "YYYY-MM-dd HH:mm:ss"));
			task.setEndTime(DateUtils.parseDate(endTime, "YYYY-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			task.setStartTime(new Date());
			task.setEndTime(new Date());
		}
		
		task.setDescription(description);
		task.setCategory(new Category(){{setId(categoryId);}});
		return task;
	}
	
	public static TaskEditForm fromEntity(Task task) {
		TaskEditForm form  = new TaskEditForm();
		form.id = task.getId();
		form.startTime = task.getStartTime().toString();
		form.endTime = task.getEndTime().toString();
		form.categoryId = task.getCategory().getId();
		form.description = task.getDescription();
		return form;
	}
}
