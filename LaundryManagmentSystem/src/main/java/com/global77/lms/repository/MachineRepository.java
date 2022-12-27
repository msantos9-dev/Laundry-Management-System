package com.global77.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global77.lms.model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

}
