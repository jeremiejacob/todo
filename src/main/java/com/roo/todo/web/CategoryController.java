package com.roo.todo.web;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roo.form.CategoryEditForm;
import com.roo.form.CategoryFilterForm;
import com.roo.todo.entity.Category;
import com.roo.todo.entity.User;
import com.roo.todo.service.CategoryService;

@RequestMapping("/category/**")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Display all Category List Screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model, @ModelAttribute("form") CategoryFilterForm form) {
		model.addAttribute("categories", categoryService.findAllCategorysByCondition(form));
		return "category/list";
	}
	
	/**
	 * Show Category
	 */
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("category", categoryService.findCategory(id));
		return "category/show";
	}
    
	/**
	 * Create New Category
	 */
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return showEditForm(model, new CategoryEditForm(), null);
	}
	
	/**
	 * Update User
	 */
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Category category = categoryService.findCategory(id);
		if (category == null) {
			redirectAttributes.addFlashAttribute("messageCode", "category_list.no.found.category");
			return "redirect:/category/list";
		} 
		return showEditForm(model, CategoryEditForm.fromEntity(category), null);
	}
	
	/**
	 * Delete Category
	 */
	@RequestMapping(method = RequestMethod.GET, value = "remove/{id}")
	public String remove(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		Category category = categoryService.findCategory(id);
		if (category == null) {
			redirectAttributes.addFlashAttribute("messageCode", "category_list.no.found.category");
		} else {
			categoryService.remove(category);
			redirectAttributes.addFlashAttribute("messageCode", "common.deleted.success");
		}
		return "redirect:/category/list";
	}
	
	private String showEditForm(Model model, CategoryEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		return "category/edit";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") CategoryEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, form, bindingResult);
		}
		Category category = form.toEntity();
		User user = new User();
		//FIXME: Dummy user_id
		user.setId(12);
		category.setUser(user);
		if (category.getId() == null) {
			categoryService.persist(category);
			redirectAttributes.addFlashAttribute("messageCode", "common.created.success");
		} else {
			categoryService.merge(category);
			redirectAttributes.addFlashAttribute("messageCode", "common.updated.success");
		}
		return "redirect:/category/list";
	}
}
