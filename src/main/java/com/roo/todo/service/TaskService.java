package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

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

	public void persist(Task task) {

	}

	public void merge(Task task) {

	}

	public void remove(Task task) {

	}
}
