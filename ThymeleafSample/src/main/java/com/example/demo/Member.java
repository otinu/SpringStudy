package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 全フィールドを初期価値にとるコンストラクタを生成
public class Member {
	private Integer id;
	private String name;
}
