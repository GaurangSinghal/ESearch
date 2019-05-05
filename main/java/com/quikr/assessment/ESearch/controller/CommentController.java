package com.quikr.assessment.ESearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quikr.assessment.ESearch.model.Comment;
import com.quikr.assessment.ESearch.service.CommentService;
import com.quikr.assessment.ESearch.service.impl.CommentProducerService;

@CrossOrigin(origins = "*")
@RestController
public class CommentController {

	@Autowired
	public CommentService commentService;
	
	@Autowired
	public CommentProducerService producer;

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public void addComment(@RequestBody Comment comment) {
		System.out.println("Inside addComment() of CommentController");
		System.out.println(comment);
		producer.storeComment(comment);
	}

	@GetMapping("/comment")
	public List<Comment> getComment(@RequestParam String txt) {
		System.out.println("Inside getComment() of CommentController");
		return commentService.getComments(txt);
	}
}
