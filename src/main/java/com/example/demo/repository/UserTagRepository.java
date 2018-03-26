package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserTag;

@Repository("userTagRepository")
public interface UserTagRepository extends JpaRepository<UserTag, Integer>
{
}