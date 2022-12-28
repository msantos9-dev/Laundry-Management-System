package com.global77.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.global77.lms.model.Store;

public interface StoreService {
	List<Store> getAllStores();
	Page<Store> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection);
	void saveStore(Store store);
	Store getStoreById(long id);

}
