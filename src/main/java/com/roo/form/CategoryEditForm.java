package com.roo.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.todo.entity.Category;

@RooJavaBean
public class CategoryEditForm {

	private Integer id;
	
	@NotEmpty
	@Length(max = 50, min = 4)
	private String type;
	
	/**
     * Generate Category entity object from this instance values.
     */
	
	public Category toEntity() {
		Category category = new Category();
		category.setId(id);
		category.setType(type);
		return category;
	}
	
	public static CategoryEditForm fromEntity(Category category) {
		CategoryEditForm form = new CategoryEditForm();
		form.setId(category.getId());
		form.setType(category.getType());
		return form;
	}
}
