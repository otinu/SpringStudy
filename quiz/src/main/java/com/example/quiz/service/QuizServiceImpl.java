package com.example.quiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;

@Service

// クラスに@Transactional を付けた場合、クラス内の全メソッドにトランザクション制御が付与される
// メソッドに@Transactional を付けた場合はそのメソッドのみ
@Transactional
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	QuizRepository repository;

	@Override
	public Iterable<Quiz> selectAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Quiz> selectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Quiz> selectOneRandom() {
		
		// 独自のSQLを作りたい場合、インターフェース側で@Queryを使って作成する。
		Integer randId = repository.getRandomId();
		if(randId == null) {
			return Optional.empty();
		}
		return repository.findById(randId);
	}

	@Override
	public Boolean checkQuiz(Integer id, Boolean myAnswer) {
		Boolean check = false;
		Optional<Quiz> optQuiz = repository.findById(id);
		if(optQuiz.isPresent()) {
			Quiz quiz = optQuiz.get();
			if(quiz.getAnswer().equals(myAnswer)) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public void insertQuiz(Quiz quiz) {
		repository.save(quiz);
	}

	@Override
	public void updateQuiz(Quiz quiz) {
		repository.save(quiz);
	}

	@Override
	public void deleteQuizById(Integer id) {
		repository.deleteById(id);
	}

}
