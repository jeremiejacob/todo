package com.roo.todo.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.validation.Valid;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roo.form.TaskEditForm;
import com.roo.form.TaskFilterForm;
import com.roo.todo.entity.Task;
import com.roo.todo.entity.User;
import com.roo.todo.service.CategoryService;
import com.roo.todo.service.TaskService;

@RequestMapping("/task/**")
@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * Display task list screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model, @ModelAttribute("form") TaskFilterForm form) {
		model.addAttribute("categories", categoryService.findAllCategorys());
		model.addAttribute("tasks", taskService.findAllTasksByCondition(form));
		model.addAttribute("dateTimeNow", new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		return "task/list";
	}
	
	/**
	 * Display show list screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("task", taskService.findTask(id));
		return "task/show";
	}
	
	/**
	 * Display create task screen
	 */
	@RequestMapping(method = RequestMethod.GET, value= "create")
	public String create(Model model) {
		return showEditForm(model, new TaskEditForm(), null);
	}
	
	/**
	 * Save data from user
	 */
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") TaskEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()) {
			return showEditForm(model, form, bindingResult);
		}
		Task task = form.toEntity();
		User user = new User();
		//FIXME: Dummy user_id
		user.setId(12);
		task.setUser(user);
		if (task.getId() == null) {
			taskService.persist(task);
		} else {
			taskService.merge(task);
		}
		return "redirect:/task/list";
	}
	/**
	 * Update Task
	 */
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		Task task  = taskService.findTask(id);
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
		Task task = taskService.findTask(id);
		if (task == null) {
			redirectAttributes.addFlashAttribute("messageCode", "task_list.no.found.task");
		} else {
			taskService.remove(task);
			redirectAttributes.addFlashAttribute("messageCode", "common.deleted.success");
		}
		return "redirect:/task/list";
	}
	
	private String showEditForm(Model model, TaskEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		model.addAttribute("categories", categoryService.findAllCategorys());
		return "task/edit";
	}
}
