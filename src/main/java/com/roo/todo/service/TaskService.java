package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roo.form.TaskFilterForm;
import com.roo.todo.entity.Task;

@Service
public class TaskService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Task> findAllTasks() {
		return entityManager.createQuery("SELECT o FROM Task o", Task.class).getResultList();
	}

	public Task findTask(Integer id) {
		return entityManager.find(Task.class, id);
	}

	public List<Task> findAllTasksByCondition(TaskFilterForm filter) {
		
		return null;
	}
	
	@Transactional
	public void persist(Task task) {
		entityManager.persist(task);
	}

	@Transactional
	public void merge(Task task) {
		entityManager.merge(task);
	}

	@Transactional
	public void remove(Task task) {
		entityManager.remove(task);
	}
}
