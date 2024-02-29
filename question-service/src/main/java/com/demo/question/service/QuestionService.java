package com.demo.question.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.demo.question.entity.Question;
import com.demo.question.entity.QuestionWrapper;
import com.demo.question.entity.Response;

public interface QuestionService {

	public ResponseEntity<List<Question>> getAllQuestions();

	public ResponseEntity<Question> addQuestion(Question question);

	public ResponseEntity<List<Question>> getQuestionByCategory(String category);

	public String removeQuestion(Question question);

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String catgory, Integer numQuestions);

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId);

	public ResponseEntity<Integer> getScore(List<Response> responses);
}
