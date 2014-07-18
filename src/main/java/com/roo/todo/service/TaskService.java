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
		String query = "SELECT o FROM Task o WHERE category.id=:categoryId";
		if (filter.getCategoryId() == null) {
			return entityManager.createQuery("SELECT o FROM Task o WHERE category.id is NOT NULL", Task.class).getResultList();
		} else {
			return entityManager.createQuery(query, Task.class).setParameter("categoryId", filter.getCategoryId()).getResultList();
		}
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
