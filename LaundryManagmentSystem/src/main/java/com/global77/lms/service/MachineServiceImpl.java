package com.global77.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.global77.lms.model.Machine;
import com.global77.lms.repository.MachineRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachineRepository machineRepository;

	@Override
	public Page<Machine> findPaginatedMachines(int pageNo, int pageSize,
			String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.machineRepository.findAll(pageable);
	}

	@Override
	public void saveMachine(Machine machine) {
		// TODO Auto-generated method stub
		machineRepository.save(machine);
	}

	@Override
	public Machine getMachineById(long id) {
		// TODO Auto-generated method stub
		Optional<Machine> optional = machineRepository.findById(id);
		Machine machine = null;
		if (optional.isPresent()) {
			machine = optional.get();
		} else {
			throw new RuntimeException(" Machine not found for id :: " + id);
		}
		return machine;
	}

	@Override
	public List<Machine> getAllMachines() {
		// TODO Auto-generated method stub
		return machineRepository.findAll();
	}

}
