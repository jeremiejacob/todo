package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.roo.todo.entity.User;

@Service
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<User> findAllUsers() {
		return entityManager.createQuery("SELECT o FROM User o", User.class).getResultList();
	}

	public User findUser(Integer id) {
		return null;
	}

	public void persist(User user) {

	}

	public void merge(User user) {

	}

	public void remove(User user) {

	}
}
