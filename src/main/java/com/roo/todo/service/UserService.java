package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roo.form.UserFilterForm;
import com.roo.todo.entity.User;

@Service
public class UserService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findAllUsers() {
		return entityManager.createQuery("SELECT o FROM User o", User.class).getResultList();
	}

	public User findUser(Integer id) {
		if (id == null)
			return null;
		return entityManager.createQuery("SELECT o FROM User o WHERE id=:id", User.class).setParameter("id", id).getSingleResult();
	}
	
	public List<User> findUserByCondition(UserFilterForm filter) {
		return null;
	}

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

	@Transactional
	public void merge(User user) {
		entityManager.merge(user);
	}

	@Transactional
	public void remove(User user) {
		entityManager.remove(user);
	}

}
