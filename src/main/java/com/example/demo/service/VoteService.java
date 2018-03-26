package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.VoteRepository;

@Service
public class VoteService
{
	@Autowired
	private VoteRepository voteRepository;

	public VoteService()
	{
	}

	public void createVote(Vote vote)
	{
		voteRepository.save(vote);
	}

	public Vote findVote(int id)
	{
		return voteRepository.findOne(id);
	}

	public List<Vote> findVotesByUser(User user)
	{
		return voteRepository.findByUser(user);
	}

	public void deleteVote(int id)
	{
		voteRepository.delete(id);
	}
	
	public void deleteListOfVotes(List<Vote> votes)
	{
		voteRepository.delete(votes);
	}
}