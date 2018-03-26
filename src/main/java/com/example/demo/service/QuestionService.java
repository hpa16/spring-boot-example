package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;

@Service
public class QuestionService
{
	@Autowired
	private QuestionRepository questionRepository;

	public QuestionService()
	{
	}

	public void createQuestion(Question question)
	{
		questionRepository.save(question);
	}

	public Question findQuestion(int id)
	{
		return questionRepository.findOne(id);
	}

	public void deleteQuestion(int id)
	{
		questionRepository.delete(id);
	}
}
