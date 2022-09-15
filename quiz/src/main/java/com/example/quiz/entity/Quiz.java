/*
 * Entityのことを「Domain Object」ともいう。
 * Entityは「DBのテーブルの1行」に対応し、各フィールドはDB内テーブルの各カラムに対応する
 */

package com.example.quiz.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
	
	@Id
	private Integer id;
	private String question;
	private Boolean answer;
	private String author;

}
