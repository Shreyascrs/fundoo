package com.bridgeit.fundoonotes.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonotes.model.User;
@Repository
public interface IRepository extends MongoRepository<User, String> {
	Optional<User>findUserByEmail(String email);
	Optional<User>findById(String id);
	
	
	
}
