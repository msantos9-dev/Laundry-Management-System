package com.global77.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.global77.lms.model.MachineStatus;

public interface MachineStatusService {

	List<MachineStatus> getAllMachineStatus();
	Page<MachineStatus> findPaginatedMachineStatus(int pageNo, int pageSize,
			String sortField, String sortDirection);

}
