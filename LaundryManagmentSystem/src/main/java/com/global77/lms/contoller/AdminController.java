package com.global77.lms.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.global77.lms.model.User;
import com.global77.lms.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	// @GetMapping("/")
	// public String home() {
	// return "admin/index";
	// }

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<User> page = userService.findPaginated(pageNo, pageSize, sortField,
				sortDir);
		List<User> listUsers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir",
				sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listUsers", listUsers);
		return "admin/index";
	}

	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "admin/new_user";
	}

	@PostMapping("/saveUser")
	public String saveEmployee(@ModelAttribute("user") User user) {
		// save employee to database
		userService.saveManager(user);
		return "redirect:/admin/";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.userService.deleteUserById(id);
		return "redirect:/admin/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id,
			Model model) {

		// get employee from the service
		User user = userService.getUserById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "admin/update_user";
	}

}