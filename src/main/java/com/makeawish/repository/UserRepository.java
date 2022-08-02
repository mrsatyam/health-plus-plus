package com.makeawish.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.makeawish.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

	@Query("SELECT U FROM Users U WHERE U.username = :name")
	public Users searchByUsername(@Param("name") String username);
}
