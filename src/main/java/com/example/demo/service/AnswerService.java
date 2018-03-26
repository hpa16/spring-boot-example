package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Answer;
import com.example.demo.repository.AnswerRepository;

@Service
public class AnswerService
{
	@Autowired
	private AnswerRepository answerRepository;

	public AnswerService()
	{
	}

	public void createAnswer(Answer answer)
	{
		answerRepository.save(answer);
	}

	public Answer findAnswer(int id)
	{
		return answerRepository.findOne(id);
	}

	public void deleteAnswer(int id)
	{
		answerRepository.delete(id);
	}
}