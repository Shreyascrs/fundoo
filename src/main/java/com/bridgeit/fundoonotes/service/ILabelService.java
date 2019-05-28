package com.bridgeit.fundoonotes.service;

import java.util.List;

import com.bridgeit.fundoonotes.dto.Dtolabel;

public interface ILabelService {
	public String createLabel(String token, Dtolabel dtolabel);

	public String updateLabel(Dtolabel dtolabel, String labelId, String token);

	public String deleteLabel(String token, String labelId);

	public List<Dtolabel> readAll(String token);
}
