package com.bridgeit.fundoonotes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtolabel;
import com.bridgeit.fundoonotes.model.Label;
import com.bridgeit.fundoonotes.model.User;
import com.bridgeit.fundoonotes.repository.ILabelRepository;
import com.bridgeit.fundoonotes.repository.IRepository;
import com.bridgeit.fundoonotes.utility.TokenUtility;

@Service
public class LabelServiceimpl implements ILabelService {

	@Autowired
	private IRepository repository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ILabelRepository labelrepository;
	@Autowired
	private Response response;
	@Autowired
	private TokenUtility TokenUtility; 

	@Override
	public Response createLabel(String token, Dtolabel dtolabel) {
		String userId = TokenUtility.verifyToken(token);
		Optional<User> user = repository.findById(userId);
		if (user.isPresent()) {
			User isuser = repository.findById(userId).get();
			Label label = mapper.map(dtolabel, Label.class);
			label.setUserid(isuser.getUserId());
			labelrepository.save(label);
			return response.sendresponse(200, "label created successfully", "");
		} else {
			return response.sendresponse(204, "error in creating the label", "");
		}
	}

	@Override
	public Response updateLabel(Dtolabel dtolabel, String labelId, String token) {

		String userId = TokenUtility.verifyToken(token);
		Optional<Label> label = labelrepository.findByLabelIdAndUserId(labelId, userId);
		if (label.isPresent()) {
			label.get().setLabelName(dtolabel.getLabelName());
			labelrepository.save(label.get());
			return response.sendresponse(200, "label updated", "");
		} else {
			return response.sendresponse(204, "label not updated", "");
		}

	}

	@Override
	public Response deleteLabel(String token, String labelId) {
		String userId = TokenUtility.verifyToken(token);
		Optional<Label> label = labelrepository.findByLabelIdAndUserId(labelId, userId);
		if (label.isPresent()) {
			labelrepository.delete(label.get());
			return response.sendresponse(200, "label deleted", "");
		} else {
			return response.sendresponse(204, "label not deleted", "");
		}

	}

	@Override
	public List<Dtolabel> readAll(String token) {

		String userId = TokenUtility.verifyToken(token);
		List<Label> labels = labelrepository.findByUserId(userId);
		List<Dtolabel> labellist = new ArrayList<Dtolabel>();
		for (Label label : labels) {
			Dtolabel dtolabel = mapper.map(label, Dtolabel.class);
			labellist.add(dtolabel);
		}
		return labellist;

	}

}
