package com.bridgeit.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonotes.model.Note;

@Repository
public interface INoteRepository extends MongoRepository<Note, String>{
	
	Optional<Note> findByUseridAndNoteid(String noteid,String userid);
	List<Note> findUserById(String userid);

}
