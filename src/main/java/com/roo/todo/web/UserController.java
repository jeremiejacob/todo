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

import com.roo.form.UserEditForm;
import com.roo.todo.entity.Users;

@RequestMapping("/users/**")
@Controller
public class UserController {

	/**
	 * Display all Users List Screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("users", Users.findAllUserses());
		return "users/list";
	}

	/**
	 * Show User
	 */
	@RequestMapping(method = RequestMethod.GET, value = "show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("user", Users.findUsers(id));
		return "users/show";
	}

	/**
	 * Create New User
	 */
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return showEditForm(model, new UserEditForm(), null);
	}

	/**
	 * Update User
	 */
	@RequestMapping(method = RequestMethod.GET, value = "update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		Users users = Users.findUsers(id);
		if (users == null) {
			redirectAttributes.addFlashAttribute("messageCode", "user_list.no.found.user");
			return "redirect:/users/list";
		} 
		return showEditForm(model, UserEditForm.fromEntity(users), null);
	}

	/**
	 * Delete User
	 */
	@RequestMapping(method = RequestMethod.GET, value = "remove/{id}")
	public String remove(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		Users users = Users.findUsers(id);
		if (users == null) {
			redirectAttributes.addFlashAttribute("messageCode", "user_list.no.found.user");
		} else {
			users.remove();
			redirectAttributes.addFlashAttribute("messageCode", "common.deleted.success");
		}
		return "redirect:/users/list";
	}

	private String showEditForm(Model model, UserEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		return "users/edit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") UserEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, form, bindingResult);
		}
		Users users = form.toEntity();
		if (users.getId() == null) {
			users.persist();
			redirectAttributes.addFlashAttribute("messageCode", "common.created.success");
		} else {
			users.merge();
			redirectAttributes.addFlashAttribute("messageCode", "common.updated.success");
		}
		return "redirect:/users/list";
	}

}
