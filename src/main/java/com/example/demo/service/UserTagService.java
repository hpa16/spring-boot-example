package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserTag;
import com.example.demo.repository.UserTagRepository;

@Service
public class UserTagService
{
	@Autowired
	private UserTagRepository userTagRepository;

	public UserTagService()
	{
	}

	public void createUserTag(UserTag userTag)
	{
		userTagRepository.save(userTag);
	}

	public void deleteUserTag(int id)
	{
		userTagRepository.delete(id);
	}
}