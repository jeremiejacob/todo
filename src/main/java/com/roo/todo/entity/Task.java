package com.roo.todo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", schema = "public", table = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users user;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = true)
	private Category category;

	@Column(name = "description")
	@NotNull
	private String description;

	@Column(name = "startTime")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Date startTime;

	@Column(name = "endTime")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "MM")
	private Date endTime;
	
	public Boolean getStatus() {
		return endTime == null;
	}
	
}
