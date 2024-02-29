package com.demo.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.question.entity.Question;
import com.demo.question.entity.QuestionWrapper;
import com.demo.question.entity.Response;
import com.demo.question.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@GetMapping("findByCategory/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("category") String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@DeleteMapping("remove")
	public String removeQuestion(@RequestBody Question question) {
		return questionService.removeQuestion(question);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String catgory, @RequestParam Integer numQuestions) {
		
		return questionService.getQuestionsForQuiz(catgory,numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId){
		return questionService.getQuestionsFromId(questionId);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		
		return questionService.getScore(responses);
	}
	
}
