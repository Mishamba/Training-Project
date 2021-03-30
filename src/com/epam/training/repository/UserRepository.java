package com.epam.training.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epam.training.model.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	long count();

	void delete(User arg0);

	void deleteAll();
		
	void deleteAll(Iterable<? extends User> arg0);
		
	void deleteById(String arg0);

	boolean existsById(String arg0);

	Iterable<User> findAll();

	Iterable<User> findAllById(Iterable<String> arg0);

	Optional<User> findById(String arg0);

	<S extends User> S save(S arg0);

	<S extends User> Iterable<S> saveAll(Iterable<S> arg0);

	Page<User> findAll(Pageable arg0);

	Iterable<User> findAll(Sort arg0);;
}
