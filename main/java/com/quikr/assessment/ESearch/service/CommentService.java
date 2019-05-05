package com.quikr.assessment.ESearch.service;

import java.util.List;

import com.quikr.assessment.ESearch.model.Comment;

public interface CommentService {
	public void storeComment(Comment comment);
	public List<Comment> getComments(String txt);
}
