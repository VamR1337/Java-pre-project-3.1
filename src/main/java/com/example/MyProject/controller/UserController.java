package com.example.MyProject.controller;


import com.example.MyProject.model.User;
import com.example.MyProject.servis.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
		userService.add(new User("Max"));
		userService.add(new User("Ser"));
		userService.add(new User("Oleg"));
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<User> users = userService.listUsers();
		model.addAttribute("user", users);
		return "index";
	}

	@GetMapping("/addNew")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());
		return "add";
	}

	@PostMapping("add")
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Invalid input. Please check the values.");
			return "add";
		}
		userService.add(user);
		return "redirect:/";
	}

	@PostMapping("/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.delete(id);
		return "redirect:/";
	}

	@GetMapping(value = "/update")
	public String showUpdateUserForm(@RequestParam("id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "up";
	}

	@PostMapping("/up")
	public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Invalid input. Please check the values.");
			return "up";
		}
		Long userId = user.getId();
		User existingUser = userService.getUserById(userId);
		existingUser.setName(user.getName());

		userService.update(existingUser);
		return "redirect:/";
	}
}