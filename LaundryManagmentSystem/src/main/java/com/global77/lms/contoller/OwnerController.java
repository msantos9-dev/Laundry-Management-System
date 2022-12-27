package com.global77.lms.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.global77.lms.model.Store;
import com.global77.lms.model.User;
import com.global77.lms.service.StoreService;
import com.global77.lms.service.UserService;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	@ModelAttribute("owners")
	public List<User> myOwners() {
		List<User> listOwners = new ArrayList<User>();
		int numManagers = userService.getAllOwners().size();

		System.out.println(numManagers);
		for (int i = 0; i < numManagers; i++) {
			listOwners.add(userService.getAllOwners().get(i));
			// System.out.println(learnercourseservice.getAllLearnerCourse().g);

		}
		return listOwners;
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "storeName", "asc", model);
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Store> page = storeService.findPaginated(pageNo, pageSize,
				sortField, sortDir);
		List<Store> listStores = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir",
				sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listStores", listStores);
		return "owner/index";
	}

	@GetMapping("/showNewStoreForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		Store store = new Store();
		model.addAttribute("store", store);
		return "owner/new_store";
	}

}
