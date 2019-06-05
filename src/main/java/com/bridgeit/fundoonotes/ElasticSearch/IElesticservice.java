package com.bridgeit.fundoonotes.ElasticSearch;

import java.io.IOException;
import java.util.List;

import com.bridgeit.fundoonotes.model.Note;

public interface IElesticservice {

	public String createNote(Note note)  throws IOException;
	public Note findById(String id) throws IOException;
	public String updateNote(Note note) throws IOException;
	public List<Note> readAll() throws IOException;
	public String delete(String noteid) throws IOException;
	public  List<Note> findByTitle(String title,String userid) throws IOException;
}
