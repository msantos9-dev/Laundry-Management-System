package com.global77.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.global77.lms.model.Machine;

public interface MachineService {
	List<Machine> getAllMachines();
	Page<Machine> findPaginatedMachines(int pageNo, int pageSize,
			String sortField, String sortDirection);
	void saveMachine(Machine machine);
	Machine getMachineById(long id);
}
