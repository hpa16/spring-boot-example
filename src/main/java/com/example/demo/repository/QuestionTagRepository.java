package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.QuestionTag;

@Repository("questionTagRepository")
public interface QuestionTagRepository extends JpaRepository<QuestionTag, Integer>
{
}