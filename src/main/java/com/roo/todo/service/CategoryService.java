package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.roo.todo.entity.Category;

@Service
public class CategoryService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Category> findAllCategorys() {
		return entityManager.createQuery("SELECT o FROM Category o", Category.class).getResultList();
	}

	public Category findCategory(Integer id) {
		return null;
	}

	public void persist(Category category) {

	}

	public void merge(Category category) {

	}

	public void remove(Category category) {

	}

}
