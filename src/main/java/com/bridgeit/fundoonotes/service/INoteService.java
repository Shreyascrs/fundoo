package com.bridgeit.fundoonotes.service;

import java.util.List;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtonote;

public interface INoteService {

	public Response createNote(Dtonote dtonote, String token);

	public Response updateNote(Dtonote dtonote, String token, String noteid);

	public Response deleteNote(String token, String noteId);

	public List<Dtonote> readNotes(String token);

	public Response addLabelToNote(String noteId, String token, String lableId);

	public Response removeLabelFromNote(String noteId, String token, String labelId);
}
