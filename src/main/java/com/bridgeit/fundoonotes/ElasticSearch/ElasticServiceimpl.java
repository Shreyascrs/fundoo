package com.bridgeit.fundoonotes.ElasticSearch;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgeit.fundoonotes.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticServiceimpl implements IElesticservice {

	String INDEX = "es";
	String TYPE = "createnote";
	@Autowired
	private RestHighLevelClient client;
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public String createNote(Note note) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> documentMappper = objectMapper.convertValue(note, Map.class);
		@SuppressWarnings("deprecation")

		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, note.getNoteid()).source(documentMappper);
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		return indexResponse.getResult().name();

	}

	@Override
	public Note findById(String id) throws IOException {

		GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		Map<String, Object> map = getResponse.getSource();
		return objectMapper.convertValue(map, Note.class);
	}

	@SuppressWarnings("unchecked")
	public String updateNote(Note note) throws IOException {
		Note note2 = findById(note.getNoteid());
		UpdateRequest request = new UpdateRequest(INDEX, TYPE, note2.getNoteid());
		Map<String, Object> map = objectMapper.convertValue(note, Map.class);
		request.doc(map);
		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		return response.getResult().name();
	}

	{

	}

	@Override
	public List<Note> readAll() throws IOException {

		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

		return getSearchResult(searchResponse);
	}

	private List<Note> getSearchResult(SearchResponse searchResponse) {

		SearchHit[] searchHits = searchResponse.getHits().getHits();
		List<Note> notes = new ArrayList<Note>();
		if (searchHits.length > 0) {
			Arrays.stream(searchHits)
					.forEach(hit -> notes.add(objectMapper.convertValue(hit.getSourceAsMap(), Note.class)));
		}
		return notes;
	}

	@Override
	public List<Note> findByTitle(String title, String userid) throws IOException {

		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title", title))
				.filter(QueryBuilders.termsQuery("userid", userid));
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(queryBuilder);
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.source(searchSourceBuilder);
		SearchResponse response = null;

		response = client.search(searchRequest, RequestOptions.DEFAULT);

		return getSearchResult(response);
	}

	@Override
	public String delete(String noteid) throws IOException {

		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, noteid);
		DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
		return "deleted";
	}

}