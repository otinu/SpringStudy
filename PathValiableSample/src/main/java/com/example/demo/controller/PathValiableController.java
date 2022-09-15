package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PathValiableController {
	/** 画面を表示する */
	@GetMapping("show")
	public String showView() {
		// 戻り値は「ビュー名」を返す
		return "show";
	}
	/** リンク処理 */
	// {no}のことを【プレースホルダ】という。
	// {no}⇒URLにURLで埋め込まれた値が格納される
	//		 ⇒<a th:href="@{/function/1}">の「1」など
	@GetMapping("/function/{no}")
	
	// 引数に@PathVariable + プレースホルダと同じ変数名 を指定することで
	// プレースホルダをメソッド内の変数として扱うことができる
	public String selectFunction(@PathVariable Integer no) {
		// 「ビュー名」の初期化
		String view = null;
		switch (no) {
		case 1:
			view = "pathvaliable/function1";
			break;
		case 2:
			view = "pathvaliable/function2";
			break;
		case 3:
			view = "pathvaliable/function3";
			break;
		}
		// 戻り値は「ビュー名」を返す
		return view;
	}
	/** 「ボタンA」押下処理 */
	@PostMapping(value = "send", params = "a")
	public String showAView() {
		// 戻り値は「ビュー名」を返す
		return "submit/a";
	}
	/** 「ボタンB」押下処理 */
	@PostMapping(value = "send", params = "b")
	public String showBView() {
		// 戻り値は「ビュー名」を返す
		return "submit/b";
	}
	/** 「ボタンC」押下処理 */
	@PostMapping(value = "send", params = "c")
	public String showCView() {
		// 戻り値は「ビュー名」を返す
		return "submit/c";
	}
}