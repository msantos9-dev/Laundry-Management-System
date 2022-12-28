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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.global77.lms.model.Machine;
import com.global77.lms.model.MachineStatus;
import com.global77.lms.model.Store;
import com.global77.lms.model.User;
import com.global77.lms.service.MachineService;
import com.global77.lms.service.MachineStatusService;
import com.global77.lms.service.StoreService;
import com.global77.lms.service.UserService;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private MachineStatusService machineStatusService;

	@ModelAttribute("users")
	public List<User> myOwners() {
		List<User> listOwners = new ArrayList<User>();
		int numOnwers = userService.getAllOwners().size();

		System.out.println("Owners" + numOnwers);
		for (int i = 0; i < numOnwers; i++) {
			listOwners.add(userService.getAllOwners().get(i));
			// System.out.println(learnercourseservice.getAllLearnerCourse().g);

		}
		return listOwners;
	}

	@ModelAttribute("machineStatusList")
	public List<MachineStatus> myMachineStatus() {
		List<MachineStatus> listMachineStatus = new ArrayList<MachineStatus>();
		int numMS = machineStatusService.getAllMachineStatus().size();

		System.out.println("Machine Status" + numMS);
		for (int i = 0; i < numMS; i++) {
			listMachineStatus
					.add(machineStatusService.getAllMachineStatus().get(i));
			// System.out.println(learnercourseservice.getAllLearnerCourse().g);

		}
		return listMachineStatus;
	}

	@ModelAttribute("storeList")
	public List<Store> myStoreList() {
		List<Store> listStores = new ArrayList<Store>();
		int numMS = storeService.getAllStores().size();

		System.out.println("Machine Status" + numMS);
		for (int i = 0; i < numMS; i++) {
			listStores.add(storeService.getAllStores().get(i));
			// System.out.println(learnercourseservice.getAllLearnerCourse().g);

		}
		return listStores;
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

	@PostMapping("/saveStore")
	public String saveStore(@ModelAttribute("store") Store store) {
		// save employee to database
		storeService.saveStore(store);
		return "redirect:/owner/";
	}

	@GetMapping("/showFormForStoreUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id,
			Model model) {

		// get employee from the service
		Store store = storeService.getStoreById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("store", store);
		return "owner/update_store";
	}

	@GetMapping("/machines")
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
		return "owner/machines";
	}

	@GetMapping("/showNewMachineForm")
	public String showNewMachineForm(Model model) {
		// create model attribute to bind form data
		Machine machine = new Machine();
		model.addAttribute("machine", machine);
		return "owner/new_machine";
	}

	@PostMapping("/saveMachine")
	public String saveMachine(@ModelAttribute("machine") Machine machine) {
		// save employee to database
		machineService.saveMachine(machine);
		return "redirect:/owner/machines";
	}

	@GetMapping("/showFormForMachineUpdate/{id}")
	public String showFormForMachineUpdate(@PathVariable(value = "id") long id,
			Model model) {

		// get employee from the service
		Machine machine = machineService.getMachineById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("machine", machine);
		return "owner/update_machine";
	}

	@GetMapping("/machineStatus")
	public String viewMachineStatus(Model model) {
		return findPaginatedMachines(1, "statusName", "asc", model);
	}

	@GetMapping("/machineStatusPage/{pageNo}")
	public String findPaginatedMachineStatus(
			@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<MachineStatus> page = machineStatusService
				.findPaginatedMachineStatus(pageNo, pageSize, sortField,
						sortDir);
		List<MachineStatus> listMachineStatus = page.getContent();

		System.out.println(page);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir",
				sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listMachineStatus", listMachineStatus);
		return "owner/machinea_status_list";
	}
}
