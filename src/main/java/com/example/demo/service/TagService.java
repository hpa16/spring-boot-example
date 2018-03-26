package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tag;
import com.example.demo.repository.TagRepository;

@Service
public class TagService
{
	@Autowired
	private TagRepository tagRepository;

	public TagService()
	{
	}

	public void createTag(Tag tag)
	{
		tagRepository.save(tag);
	}

	public Tag findByText(String text)
	{
		return tagRepository.findByText(text);
	}

	public void deleteTag(int id)
	{
		tagRepository.delete(id);
	}
}
