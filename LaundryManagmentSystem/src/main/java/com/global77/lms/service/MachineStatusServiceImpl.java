package com.global77.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
