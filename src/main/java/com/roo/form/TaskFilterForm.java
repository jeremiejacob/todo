package com.roo.form;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class TaskFilterForm {

	private String description;
	private Integer categoryId;
	private String startTime;
	private String endTime;
}
