package com.roo.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.Task;

@RooJavaBean
public class TaskEditForm {

	private Integer id;

	@NotEmpty
	@Length(max = 50)
	private String description;

	@NotEmpty
	private Date startTime;

	@NotEmpty
	private Date endTime;

	/**
	 * Generate Task Entity object from this instance values.
	 */

	public Task toEntity() {
		Task task = new Task();
		task.setId(id);
		task.setDescription(description);
		task.setStartTime(startTime);
		task.setEndTime(endTime);
		return task;
	}
	
	public static TaskEditForm fromEntity(Task task) {
		TaskEditForm form = new TaskEditForm();
		form.setId(task.getId());
        form.setDescription(task.getDescription());
        form.setStartTime(task.getStartTime());
        form.setEndTime(task.getEndTime());
		return form;
	}
}
