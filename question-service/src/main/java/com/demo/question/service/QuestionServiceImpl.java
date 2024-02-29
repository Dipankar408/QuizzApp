package com.demo.question.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.question.dao.QuestionDao;
import com.demo.question.entity.Question;
import com.demo.question.entity.QuestionWrapper;
import com.demo.question.entity.Response;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		return new ResponseEntity<List<Question>>(questionDao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Question> addQuestion(Question question) {
		return new ResponseEntity<Question>(questionDao.save(question), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.OK);
	}

	@Override
	public String removeQuestion(Question question) {
		questionDao.delete(question);
		return "Question delted";
	}

	@Override
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String catgory, Integer numQuestions) {
		List<Integer> questions = questionDao.findRandomQuestionsByCategory(catgory, numQuestions);
		return new ResponseEntity<>(questions, HttpStatus.ACCEPTED.OK);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		questions.addAll(questionDao.findAllById(questionId));
		
		questions.forEach(question -> wrappers.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4())));
		wrappers.forEach(wr -> System.out.println(wr));
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		for(Response response : responses) {
			Question question = questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++ ;
			}
		}
		return new ResponseEntity<Integer>(right,HttpStatus.OK);
	}
}
