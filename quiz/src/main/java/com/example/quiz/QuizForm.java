package com.example.quiz;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizForm {

	private Integer id;
	
	private String question;
	
	private Boolean answer;
	
	@NotBlank
	private String author;
	
	// 登録・変更の判定用
	private Boolean newQuiz;
}
