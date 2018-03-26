package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tag;

@Repository("tagRepository")
public interface TagRepository extends JpaRepository<Tag, Integer>
{
	Tag findByText(String text);
}