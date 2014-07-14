package com.roo.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roo.todo.entity.Task;

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
}
