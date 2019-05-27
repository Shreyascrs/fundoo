package com.bridgeit.fundoonotes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.model.Note;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.INoteRepository;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.TokenUtility;
import com.bridgeit.fundoonotes.utility.Utility;

@Service
public class Noteserviceimpl implements INoteService {

	@Autowired
	ModelMapper modelmapper;
	@Autowired
	INoteRepository iNoteRepository;
	@Autowired
	IRepository repository;

	@Override
	public String createNote(Dtonote dtonote, String token) {
		String id = TokenUtility.verifyToken(token);
		Optional<User> isUser = repository.findById(id);
		if (isUser.isPresent()) {
			User user = isUser.get();
			Note note = modelmapper.map(dtonote, Note.class);
			note.setUserid(user.getUserId());
			note.setCreatedTime(Utility.todayDate());
			note.setUpdatedTime(Utility.todayDate());
			iNoteRepository.save(note);
			return "note added successfully";

		} else {
			System.out.println("error in creation of note");
			return "error in creation of note";
		}
	}

	@Override
	public String updateNote(Dtonote dtonote, String token, String noteid) {
		String userid = TokenUtility.verifyToken(token);
		Optional<Note> isNote = iNoteRepository.findByNoteidAndUserid(noteid, userid);
		if (isNote.isPresent()) {
			isNote.get().setUpdatedTime(Utility.todayDate());
			isNote.get().setTitle(dtonote.getTitle());
			isNote.get().setDescription(dtonote.getDescription());
			iNoteRepository.save(isNote.get());

			return "note updated";
		} else {

			return "note not present";
		}
	}

	@Override
	public String deleteNote(String token, String noteId) {

		String userId = TokenUtility.verifyToken(token);
		Optional<Note> isNote = iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if (isNote.isPresent()) {
			iNoteRepository.delete(isNote.get());
			return "deleted successfully";
		} else {
			return "note not present";
		}
	}

	@Override
	public List<Dtonote> readNotes(String token) {

		String userId = TokenUtility.verifyToken(token);
		List<Note> notes = iNoteRepository.findByUserid(userId);
		List<Dtonote> listOfNotes = new ArrayList<Dtonote>();
		for (Note note : notes) {
			Dtonote dtonote = modelmapper.map(note, Dtonote.class);
			listOfNotes.add(dtonote);
		}

		return listOfNotes;

	}

	
	public String isArchive(String token,String noteId)
	{
		String userId=TokenUtility.verifyToken(token);
		Optional<Note> note=iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if(note.isPresent())
		{
			note.get().setPin(false);
			if(note.get().isArchive()==false)
			{
				note.get().setArchive(false);
			}else
			{
				note.get().setArchive(true);
			}
			note.get().setUpdatedTime(Utility.todayDate());
			iNoteRepository.save(note.get());
			return "archive operation exe successfull";
		}
		else
		{
			return "note is not present";
		}
		
	}
	public String isTrash(String token,String noteId)
	{
		String userId=TokenUtility.verifyToken(token);
		Optional<Note> note=iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if(note.isPresent())
		{
			note.get().setTrash(true);
			iNoteRepository.save(note.get());
			return "trash set as true";
		}else
		{
			return"note not present";
		}
	}
	
	public String isPin(String token,String noteId)
	{
		String userId=TokenUtility.verifyToken(token);
		Optional<Note> note=iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if(note.isPresent())
		{
			note.get().setArchive(false);
			note.get().setPin(true);
			note.get().setUpdatedTime(Utility.todayDate());
			iNoteRepository.save(note.get());
			return "is verified";
		}else
		{
			return "note not present";
		}
	}
}
