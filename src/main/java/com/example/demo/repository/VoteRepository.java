package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.model.Vote;

@Repository("voteRepository")
public interface VoteRepository extends JpaRepository<Vote, Integer>
{
	List<Vote> findByUser(User user);
}