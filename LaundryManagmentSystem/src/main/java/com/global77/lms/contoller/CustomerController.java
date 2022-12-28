package com.global77.lms.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.global77.lms.model.Machine;
import com.global77.lms.service.MachineService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private MachineService machineService;

	// @GetMapping("/")
	// public String home() {
	// return "customer/index";
	// }

	@GetMapping("/")
	public String viewMachines(Model model) {
		return findPaginatedMachines(1, "description", "asc", model);
	}

	@GetMapping("/machinesPage/{pageNo}")
	public String findPaginatedMachines(
			@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Machine> page = machineService.findPaginatedMachines(pageNo,
				pageSize, sortField, sortDir);
		List<Machine> listMachines = page.getContent();

		System.out.println(page);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir",
				sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listMachines", listMachines);
		return "customer/index";
	}

}
