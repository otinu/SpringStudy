package com.example.quiz.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.quiz.entity.Quiz;

// 第一引数 ⇒ 保存対象のオブジェクトの型
// 第二引数 ⇒ 保存対象のオブジェクトの主キーの型(ここではid)
public interface QuizRepository extends CrudRepository<Quiz, Integer> {

	@Query("SELECT id FROM quiz ORDER BY RANDOM() limit 1")
	Integer getRandomId();
}
