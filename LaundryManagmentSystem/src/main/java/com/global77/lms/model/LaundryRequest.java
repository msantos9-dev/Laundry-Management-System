package com.global77.lms.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
@Table(name = "laundryRequest")
public class LaundryRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Machine machine;

	@ManyToOne
	private User customer;

	@OneToOne
	private RequestStatus requestStatus;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;

	private Double amount;
}
