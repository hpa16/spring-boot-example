package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.QuestionTag;
import com.example.demo.repository.QuestionTagRepository;

@Service
public class QuestionTagService
{
	@Autowired
	private QuestionTagRepository questionTagRepository;

	public QuestionTagService()
	{
	}

	public void createQuestionTag(QuestionTag questionTags)
	{
		questionTagRepository.save(questionTags);
	}

	public void deleteQuestionTag(int id)
	{
		questionTagRepository.delete(id);
	}
}
