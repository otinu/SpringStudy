package com.example.quiz.service;

import java.util.Optional;

import com.example.quiz.entity.Quiz;

public interface QuizService {
	
	Iterable<Quiz> selectAll();
	
	Optional<Quiz> selectOneById(Integer id);
	
	Optional<Quiz> selectOneRandom();
	
	// 正解or不正解を判定
	Boolean checkQuiz(Integer id, Boolean myAnswer);
	
	// クイズを登録
	void insertQuiz(Quiz quiz);
	
	void updateQuiz(Quiz quiz);
	
	void deleteQuizById(Integer id);

}
