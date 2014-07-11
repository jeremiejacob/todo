package com.roo.todo.web;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roo.form.TaskEditForm;
import com.roo.todo.entity.Task;
import com.roo.todo.entity.Users;

@RequestMapping("/task/**")
@Controller
public class TaskController {

	/**
	 * Display all Tasks List Screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("tasks", Task.findAllTasks());
		return "tasks/list";
	}
	
	/**
	 * Show Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("task", Task.findTask(id));
		return "task/show";
	}
    
	/**
	 * Create New Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return showEditForm(model, new TaskEditForm(), null);
	}
	
	/**
	 * Update Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Task task = Task.findTask(id);
		if (task == null) {
			redirectAttributes.addFlashAttribute("messageCode", "task_list.no.found.task");
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
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") TaskEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, form, bindingResult);
		}
		Task task = form.toEntity();
		if (task.getId() == null) {
			//FIXME: Dummy user_id
			Users user = new Users();
			user.setId(12);
			task.setUser(user);
			task.persist();
			redirectAttributes.addFlashAttribute("messageCode", "common.created.success");
		} else {
			task.merge();
			redirectAttributes.addFlashAttribute("messageCode", "common.updated.success");
		}
		return "redirect:/task/list";
	}
}
