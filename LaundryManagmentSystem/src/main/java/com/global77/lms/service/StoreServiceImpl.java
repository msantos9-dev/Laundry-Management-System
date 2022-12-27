package com.global77.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.global77.lms.model.Store;
import com.global77.lms.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		return storeRepository.findAll();
	}

	@Override
	public Page<Store> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.storeRepository.findAll(pageable);
	}

	@Override
	public void saveStore(Store store) {
		// TODO Auto-generated method stub
		storeRepository.save(store);
	}

	@Override
	public Store getStoreById(long id) {
		// TODO Auto-generated method stub
		Optional<Store> optional = storeRepository.findById(id);
		Store store = null;
		if (optional.isPresent()) {
			store = optional.get();
		} else {
			throw new RuntimeException(" Store not found for id :: " + id);
		}
		return store;
	}

}
