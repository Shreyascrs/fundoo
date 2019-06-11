package com.bridgeit.fundoonotes.service;

import java.util.List;

import com.bridgeit.fundoonotes.Response.Response;
import com.bridgeit.fundoonotes.dto.Dtolabel;

public interface ILabelService {
	public Response createLabel(String token, Dtolabel dtolabel);

	public Response updateLabel(Dtolabel dtolabel, String labelId, String token);

	public Response deleteLabel(String token, String labelId);

	public List<Dtolabel> readAll(String token);
}
