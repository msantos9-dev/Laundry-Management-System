package com.global77.lms.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "laundryRequest")
public class LaundryRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String machineID;

	private String customerID;

	private String requestStatusID;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;

	private Double amount;
}
