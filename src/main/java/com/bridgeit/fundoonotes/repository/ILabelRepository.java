package com.bridgeit.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonotes.model.Label;

@Repository
public interface ILabelRepository extends MongoRepository<Label, String>

{
	Optional<Label> findByLabelId(String noteId);

	Optional<Label> findByLabelIdAndUserId(String LabelId, String userId);

	List<Label> findByUserId(String userId);
}
