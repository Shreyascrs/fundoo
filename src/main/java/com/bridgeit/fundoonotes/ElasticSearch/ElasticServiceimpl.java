package com.bridgeit.fundoonotes.ElasticSearch;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonotes.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticServiceimpl implements IElesticservice{

	String INDEX="es";
	String TYPE="createnote";
		@Autowired
	    private RestHighLevelClient  client;
		@Autowired
		ObjectMapper objectMapper;

	@Override
	public String createNote(Note note) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String , Object> documentMappper = objectMapper.convertValue(note, Map.class);
		@SuppressWarnings("deprecation")
		IndexRequest indexRequest= new IndexRequest(INDEX,TYPE,note.getNoteid()).source(documentMappper);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		 return indexResponse.getResult().name();
	
	}

}