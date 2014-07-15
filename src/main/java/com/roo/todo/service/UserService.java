package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roo.todo.entity.User;

@Service
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> findAllUsers() {
		return entityManager.createQuery("SELECT o FROM User o", User.class).getResultList();
	}

	public User findUser(Integer id) {
		if (id == null) return null;
		return entityManager.find(User.class, id);
	}

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

	public void merge(User user) {

	}

	public void remove(User user) {

	}
}
