package com.global77.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global77.lms.model.LaundryRequest;

@Repository
public interface LaundryRequestRepository
		extends
			JpaRepository<LaundryRequest, Long> {

}
