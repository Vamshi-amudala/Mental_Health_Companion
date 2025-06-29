package com.example.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	boolean existsByEmail(@Param("email") String email);
	
	Optional<User> findByemail(String email);

}
