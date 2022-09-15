package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.Form;

// ここでMappingアノテーションを設定していない
// ⇒「http://～/」がこのコントローラの対象URLになる
@Controller
public class RequestParamController {
	/** 入力画面を表示する */
	@GetMapping("show")
	public String showView() {
		// 戻り値は「ビュー名」を返す
		return "entry";
	}
	/** 確認画面を表示する */
//	@PostMapping("confirm")
//	public String confirmView(
//			Model model, @RequestParam String name, @RequestParam Integer age,
//			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate birth) {
//		// Modelに格納する
//		model.addAttribute("name", name);
//		model.addAttribute("age", age);
//		model.addAttribute("birth", birth);
//		// 戻り値は「ビュー名」を返す
//		return "confirm";
//	}
	/** 確認画面を表示する：Formクラス使用 */
	@PostMapping("confirm")
	
	// この書き方の場合、第一引数にModel型の変数を指定しなくても、
	// 自動的にModelに格納された状態になる
	public String confirmView(Form f) {
		// 戻り値は「ビュー名」を返す
		return "confirm2";
	}
}