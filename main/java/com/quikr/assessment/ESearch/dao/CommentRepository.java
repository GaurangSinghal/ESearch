package com.quikr.assessment.ESearch.dao;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quikr.assessment.ESearch.model.Comment;

@Repository
public class CommentRepository {

	@Autowired
	private Client client;

	private final String INDEX = "commentindex";
	private final String TYPE = "comments";

	public IndexResponse addComment(Comment comment){
		System.out.println("Inside addComment() of CommentRepository");

		IndexResponse response=null;
		try {
			response = client.prepareIndex(INDEX, TYPE)
					.setSource(jsonBuilder().startObject().field("emailId", comment.getEmailId())
							.field("comment", comment.getComment()).field("date", comment.getDate()).endObject())
					.get();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	public SearchResponse getComments(String txt) {
		BoolQueryBuilder qb = QueryBuilders.boolQuery();
		QueryBuilder qb1 = QueryBuilders.matchPhraseQuery("comment", txt).boost(100);
		QueryBuilder qb2 = QueryBuilders.matchQuery("comment", txt).boost(1);
		QueryBuilder qb3 = QueryBuilders.matchQuery("comment", txt).operator(Operator.AND).boost(10);
		qb.should(qb1);
		qb.should(qb2);
		qb.should(qb3);

		SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE).setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(qb).get();

		return response;
	}

}
