package com.bridgeit.fundoonotes.service;

import java.util.List;

import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.model.Note;

public interface INoteService {

	public String createNote(Dtonote dtonote,String token);
	public String updateNote(Dtonote dtonote,String token,String noteid);
	public String deleteNote(String token,String noteId);
	public List<Dtonote> readNotes(String token);
	public String addLabelToNote(String noteId,String token,String lableId);
	public String removeLabelFromNote(String noteId,String token,String labelId);
}
