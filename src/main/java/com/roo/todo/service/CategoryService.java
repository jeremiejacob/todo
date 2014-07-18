package com.roo.todo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.roo.form.CategoryFilterForm;
import com.roo.todo.entity.Category;

@Service
public class CategoryService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Category> findAllCategorys() {
		return entityManager.createQuery("SELECT o FROM Category o", Category.class).getResultList();
	}

	public Category findCategory(Integer id) {
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> findAllCategorysByCondition(CategoryFilterForm filter) {
		String query = "SELECT o FROM Category o WHERE type LIKE :type";
		String value = filter.getType() == null ? "" : filter.getType();
		return entityManager.createQuery(query, Category.class).setParameter("type", "%" + value + "%").getResultList();
	}

	@Transactional
	public void persist(Category category) {
		entityManager.persist(category);
	}

	@Transactional
	public void merge(Category category) {
		entityManager.merge(category);
	}
	
	@Transactional
	public void remove(Category category) {
		entityManager.remove(category);
	}

}
