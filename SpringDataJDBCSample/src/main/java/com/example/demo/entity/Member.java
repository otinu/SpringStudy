package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 全フィールドにゲッター/セッターを作成したり、equals()やtoString()などを使えるようにしてくれる
@Data

// デフォルトコンストラクタを自動生成
@NoArgsConstructor

// 全フィールドに対する、引数付きコンストラクタを自動生成
@AllArgsConstructor
public class Member {
	@Id
	private Integer id;
	
	private String name;
}
