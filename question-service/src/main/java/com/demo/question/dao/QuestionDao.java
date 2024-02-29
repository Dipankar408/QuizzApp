package com.demo.question.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.question.entity.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

public List<Question> findByCategory(String category);
	
	@Query(value = "SELECT id FROM question q WHERE q.category = :category LIMIT :num", nativeQuery = true)
	public List<Integer> findRandomQuestionsByCategory(String category, int num);
}
