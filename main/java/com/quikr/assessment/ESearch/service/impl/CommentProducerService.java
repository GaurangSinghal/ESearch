package com.quikr.assessment.ESearch.service.impl;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quikr.assessment.ESearch.model.Comment;

@Service
public class CommentProducerService {
	
	@Autowired
	public RabbitTemplate template;
	
//	@Autowired
//	public Exchange exchange;

	@Autowired
	private MessageConverter messageConverter;

	public void storeComment(Comment comment) {
		System.out.println("Inside storeComment() of CommentProducer");
		
		String routingKey = "comment.msg";
		template.convertAndSend("eventExchange", routingKey, comment);
	}
}
