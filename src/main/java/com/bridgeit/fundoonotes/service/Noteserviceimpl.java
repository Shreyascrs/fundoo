package com.bridgeit.fundoonotes.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.ElasticSearch.IElesticservice;
import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.exception.NoteException;
import com.bridgeit.fundoonotes.model.Label;
import com.bridgeit.fundoonotes.model.Note;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.ILabelRepository;
import com.bridgeit.fundoonotes.repository.INoteRepository;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.TokenUtility;
import com.bridgeit.fundoonotes.utility.Utility;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class Noteserviceimpl implements INoteService {

	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private INoteRepository iNoteRepository;
	@Autowired
	private IRepository repository;
	@Autowired
	private ILabelRepository labelrepository;
	@Autowired
	private IElesticservice elasticSearch;
	@Autowired
	private Response response;
	@Autowired
	private TokenUtility TokenUtility;
	
	@Override
	public Response createNote(Dtonote dtonote, String token) {
		String id = TokenUtility.verifyToken(token);
		Optional<User> isUser = repository.findById(id);
		if (isUser.isPresent()) {
			User user = isUser.get();
			Note note = modelmapper.map(dtonote, Note.class);
			note.setUserid(user.getUserId());
			note.setCreatedTime(Utility.todayDate());
			note.setUpdatedTime(Utility.todayDate());
			Note savedNote = iNoteRepository.save(note);
			try {
				elasticSearch.createNote(savedNote);
			} catch (IOException e) {
				throw new NoteException("error in elastic search creation of note");
			}
			return response.sendresponse(200, "note created", "");

		} else {
			return response.sendresponse(204, "note not created", "");

		}
	}

	@Override
	public Response updateNote(Dtonote dtonote, String token, String noteid) {
		String userid = TokenUtility.verifyToken(token);
		Optional<Note> isNote = iNoteRepository.findByNoteidAndUserid(noteid, userid);
		if (isNote.isPresent()) {
			isNote.get().setUpdatedTime(Utility.todayDate());
			isNote.get().setTitle(dtonote.getTitle());
			isNote.get().setDescription(dtonote.getDescription());
			Note updatedNote = iNoteRepository.save(isNote.get());
			try {
				elasticSearch.updateNote(updatedNote);
			} catch (IOException e) {

				throw new NoteException("error in elastic search updation of note");
			}
			return response.sendresponse(200, "note updated", "");
		} else {

			return response.sendresponse(400, "note not updated", "");
		}
	}

	@Override
	public Response deleteNote(String token, String noteId) {

		String userId = TokenUtility.verifyToken(token);
		Optional<Note> isNote = iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if (isNote.isPresent()) {
			iNoteRepository.delete(isNote.get());
			try {
				elasticSearch.delete(noteId);
			} catch (IOException e) {
				throw new NoteException("something went wrong");

			}
			return response.sendresponse(200, "note deleted successfully", "");
		} else {
			return response.sendresponse(400, "note not deleted", "");
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
		try {
			elasticSearch.readAll();
		} catch (IOException e) {
			throw new NoteException("something went wrong");
		}
		return listOfNotes;

	}

	public String isArchive(String token, String noteId) {
		String userId = TokenUtility.verifyToken(token);
		Optional<Note> note = iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if (note.isPresent()) {
			note.get().setPin(false);
			if (note.get().isArchive() == false) {
				note.get().setArchive(false);
			} else {
				note.get().setArchive(true);
			}
			note.get().setUpdatedTime(Utility.todayDate());
			iNoteRepository.save(note.get());
			return "archive operation exe successfull";
		} else {
			return "note is not present";
		}

	}

	public String isTrash(String token, String noteId){
		String userId = TokenUtility.verifyToken(token);
		Optional<Note> note = iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if (note.isPresent()) {
			note.get().setTrash(true);
			iNoteRepository.save(note.get());
			return "trash set as true";
		} else {
			return "note not present";
		}
	}

	public String isPin(String token, String noteId){
		String userId = TokenUtility.verifyToken(token);
		Optional<Note> note = iNoteRepository.findByNoteidAndUserid(noteId, userId);
		if (note.isPresent()) {
			note.get().setArchive(false);
			note.get().setPin(true);
			note.get().setUpdatedTime(Utility.todayDate());
			iNoteRepository.save(note.get());
			return "is verified";
		} else {
			return "note not present";
		}
	}

	@Override
	public Response addLabelToNote(String noteId, String token, String lableId){
		String userId = TokenUtility.verifyToken(token);
		Optional<User> optuser = repository.findById(userId);
		Optional<Note> optnote = iNoteRepository.findById(noteId);
		Optional<Label> optlabel = labelrepository.findById(lableId);
		if (optuser.isPresent() && optlabel.isPresent() && optnote.isPresent()) {
			Label label = optlabel.get();
			Note note = optnote.get();
			note.setUpdatedTime(Utility.todayDate());
			List<Label> labels = note.getLabels();
			if (labels != null) {
				Optional<Label> oplabel = labels.stream().filter(l -> l.getLabelid().equals(label.getLabelid()))
						.findFirst();
				if (!oplabel.isPresent()) {
					labels.add(label);
					note.setLabels(labels);
					note = iNoteRepository.save(note);
					System.out.println("saved labels in note");
					return response.sendresponse(200, "label added to note", "");
				}
			} else if (labels == null) {
				labels = new ArrayList<Label>();
				labels.add(label);
				note.setLabels(labels);
				iNoteRepository.save(note);
				return response.sendresponse(200, "label added to note", "");

			} else {
				return response.sendresponse(204, "error in adding label to note", "");
			}

		}else
		{
			return response.sendresponse(204, "error in adding label to note", "");
		}
		
		return response.sendresponse(204, "error in adding label to note", "");
	}

	public Response removeLabelFromNote(String noteId, String token, String labelId){
		String userId = TokenUtility.verifyToken(token);
		Optional<Note> optnote = iNoteRepository.findById(noteId);
		Optional<Label> optlabel = labelrepository.findById(labelId);
		Optional<User> optuser = repository.findById(userId);
		if (optuser.isPresent() && optlabel.isPresent() && optnote.isPresent()) {
			Label label = optlabel.get();
			Note note = optnote.get();
			note.setUpdatedTime(Utility.todayDate());
			List<Label> labellist = new ArrayList<Label>();
			labellist = note.getLabels();
			if (labellist.stream().filter(l -> l.getLabelid().equals(label.getLabelid())).findFirst().isPresent()) {
				Label findlabel = labellist.stream().filter(l -> l.getLabelid().equals(label.getLabelid())).findFirst()
						.get();
				labellist.remove(findlabel);
				iNoteRepository.save(note);
				return response.sendresponse(200, "label removed from note", "");
			}
			return response.sendresponse(204, "label not removed from note", "");
		}
		return response.sendresponse(200, "not present", "");
	}
}
