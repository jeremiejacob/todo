package com.roo.todo.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roo.form.CategoryEditForm;
import com.roo.form.TaskEditForm;
import com.roo.todo.entity.Category;
import com.roo.todo.entity.Task;
import com.roo.todo.entity.Users;

@RequestMapping("/task/**")
@Controller
public class TaskController {

	/**
	 * Display task list screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("tasks", Task.findAllTasks());
		return "task/list";
	}
	
	/**
	 * Display show list screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("task", Task.findTask(id));
		return "task/show";
	}
	
	/**
	 * Display create task screen
	 */
	@RequestMapping(method = RequestMethod.GET, value= "create")
	public String create(Model model) {
		model.addAttribute("form", new TaskEditForm());
		model.addAttribute("categories", Category.findAllCategorys());
		return "task/edit";
	}
	
	/**
	 * Save data from user
	 */
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") TaskEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()) {
			return "task/edit";
		}
		Task task = form.toEntity();
		Users user = new Users();
		//FIXME: Dummy user_id
		user.setId(12);
		task.setUser(user);
		task.persist();
		return "redirect:task/list";
	}
}
