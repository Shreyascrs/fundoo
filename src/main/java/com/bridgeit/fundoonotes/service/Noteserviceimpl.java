package com.bridgeit.fundoonotes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.dto.Dtonote;
import com.bridgeit.fundoonotes.model.Label;
import com.bridgeit.fundoonotes.model.Note;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.ILabelRepository;
import com.bridgeit.fundoonotes.repository.INoteRepository;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.TokenUtility;
import com.bridgeit.fundoonotes.utility.Utility;

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

	public String isTrash(String token, String noteId) {
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

	public String isPin(String token, String noteId) {
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
	public String addLabelToNote(String noteId, String token, String lableId) {
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
				Optional<Label> oplabel = labels.stream().filter(l -> l.getLabelid().equals(label.getLabelName()))
						.findFirst();
				if (!oplabel.isPresent()) {
					labels.add(label);
					note.setLabels(labels);
					note = iNoteRepository.save(note);
					System.out.println("saved labels in note");
					return "label saved in note";
				}
			} else if (labels == null) {
				labels = new ArrayList<Label>();
				labels.add(label);
				note.setLabels(labels);
				iNoteRepository.save(note);
				return "label added";

			} else {
				return "error in adding labels";
			}

		}
		return "error in adding label";
	}
	
	public String removeLabelFromNote(String noteId,String token,String labelId)
	{
		String userId=TokenUtility.verifyToken(token);
		Optional<Note> optnote=iNoteRepository.findById(noteId);
		Optional<Label> optlabel=labelrepository.findById(labelId);
		Optional<User> optuser=repository.findById(userId);
		if(optuser.isPresent() && optlabel.isPresent() && optnote.isPresent())
		{
			Label label=optlabel.get();
			Note note=optnote.get();
			note.setUpdatedTime(Utility.todayDate());
			List<Label> labellist=new ArrayList<Label>();
			labellist=note.getLabels();
			if(labellist.stream().filter(l-> l.getLabelid().equals(label.getLabelid())).findFirst().isPresent())
			{
				Label findlabel=labellist.stream().filter(l->l.getLabelid().equals(label.getLabelid())).findFirst().get();
				labellist.remove(findlabel);
				iNoteRepository.save(note);
				return "note saved successfully";
			}
			return "label not removed";
		}
		return null;
	}
}
