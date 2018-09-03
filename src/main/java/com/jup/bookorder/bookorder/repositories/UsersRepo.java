package com.jup.bookorder.bookorder.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.jup.bookorder.bookorder.entities.User;

public interface UsersRepo extends CrudRepository<User, Long> {
	List<User> findAll();
	Page<User> findAll(Pageable pageable);
}
