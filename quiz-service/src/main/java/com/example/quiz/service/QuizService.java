package com.example.quiz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.quiz.entity.QuestionWrapper;
import com.example.quiz.entity.Response;


public interface QuizService {

public ResponseEntity<String> createQuiz(String category, int num, String title);
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses);
}
