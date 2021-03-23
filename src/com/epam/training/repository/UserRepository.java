package com.epam.training.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.epam.training.model.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
