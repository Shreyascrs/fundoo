package com.bridgeit.fundoonotes.ElasticSearch;

import java.io.IOException;

import com.bridgeit.fundoonotes.model.Note;

public interface IElesticservice {

	public String createNote(Note note)  throws IOException;
}
