package com.quikr.assessment.ESearch.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.ElasticsearchParseException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quikr.assessment.ESearch.dao.CommentRepository;
import com.quikr.assessment.ESearch.model.Comment;
import com.quikr.assessment.ESearch.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Override
//	@RabbitListener(queues = "commentQueue") /* uncomment this statement for consumer*/
	public void storeComment(Comment comment) {
		System.out.println("Inside storeComment() of CommentServiceImpl");
	
		IndexResponse response=repository.addComment(comment);
		System.out.println("response id:" + response.getId());
		System.out.println(response);
		System.out.println(response.getResult().toString());

	}

	@Override
	public List<Comment> getComments(String txt) {
		System.out.println("Inside getComments() of CommentServiceImpl");
		List<Comment> res = new ArrayList<>();
		try {
			SearchResponse response = repository.getComments(txt);
			List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());

			for (SearchHit hit : searchHits) {
				res.add(new Comment(hit.getSourceAsMap().get("emailId").toString(),
						hit.getSourceAsMap().get("comment").toString(),
						format.parse(hit.getSourceAsMap().get("date").toString())));

				System.out.println(hit.getSource());
				System.out.println();
			}
		} catch (ElasticsearchParseException | ParseException e) {
			e.printStackTrace();
		}

		return res;
	}
}
