package com.global77.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.global77.lms.model.MachineStatus;
import com.global77.lms.repository.MachineStatusRepository;

@Service
public class MachineStatusServiceImpl implements MachineStatusService {

	@Autowired
	private MachineStatusRepository machineStatusRepository;

	@Override
	public List<MachineStatus> getAllMachineStatus() {
		// TODO Auto-generated method stub
		return machineStatusRepository.findAll();
	}

	@Override
	public Page<MachineStatus> findPaginatedMachineStatus(int pageNo,
			int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.machineStatusRepository.findAll(pageable);
	}

}
