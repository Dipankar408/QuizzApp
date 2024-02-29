package com.example.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.dao.QuizDao;
import com.example.quiz.entity.QuestionWrapper;
import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.Response;
import com.example.quiz.feign.QuizFeignInterface;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	QuizFeignInterface quizFeignInterface;
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int num, String title){
		List<Integer> questions = quizFeignInterface.getQuestionsForQuiz(category, num).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setQuestionIds(questions);
		quiz.setTitle(title);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
		Quiz quiz = quizDao.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		questionIds.forEach(System.out::println);
		ResponseEntity<List<QuestionWrapper>> questions = quizFeignInterface.getQuestionsFromId(questionIds);
		
		System.out.println(questions.getBody());
		return questions;
	}

	@Override
	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		return  quizFeignInterface.getScore(responses);
	}
}
