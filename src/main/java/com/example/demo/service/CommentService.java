package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService
{
	@Autowired
	private CommentRepository commentRepository;

	public CommentService()
	{
	}

	public void createComment(Comment comment)
	{
		commentRepository.save(comment);
	}

	public Comment findComment(int id)
	{
		return commentRepository.findOne(id);
	}

	public List<Comment> findCommentsByUser(User user)
	{
		return commentRepository.findByUser(user);
	}

	public void deleteComment(int id)
	{
		commentRepository.delete(id);
	}
	public void deleteListOfComments(List<Comment> comments)
	{
		commentRepository.delete(comments);
	}
}
