package com.epam.training.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.epam.training.model.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
