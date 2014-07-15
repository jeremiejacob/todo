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
import com.roo.todo.entity.User;

@RequestMapping("/task/**")
@Controller
public class TaskController {

	private static final Logger logger = Logger.getLogger(TaskEditForm.class);
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
		User user = new User();
		//FIXME: Dummy user_id
		user.setId(12);
		task.setUser(user);
		if (task.getId() ==null) {
			task.persist();
		} else {
			task.merge();
		}
		return "redirect:/task/list";
	}
	/**
	 * Update Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		Task task  = Task.findTask(id);
		model.addAttribute("categories", Category.findAllCategorys());
		if (task == null) {
			return "redirect:/task/list";
		}
		return showEditForm(model, TaskEditForm.fromEntity(task), null);
	}
	
	/**
	 * Delete Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "remove/{id}")
	public String remove(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		Task task = Task.findTask(id);
		if (task == null) {
			redirectAttributes.addFlashAttribute("messageCode", "task_list.no.found.task");
		} else {
			task.remove();
			redirectAttributes.addFlashAttribute("messageCode", "common.deleted.success");
		}
		return "redirect:/task/list";
	}
	
	private String showEditForm(Model model, TaskEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		return "task/edit";
	}
}
