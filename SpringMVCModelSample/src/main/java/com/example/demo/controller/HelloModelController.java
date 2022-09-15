package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloModelController {

	@GetMapping("model")
	// ここでリクエストハンドラメソッドの引数にModel型を渡している
	public String helloView(Model model) {
		
		// ①メソッドのスコープ内でインスタンスmodelを生成していないのに、使えている
		// 　⇒Spring MVCによってインスタンスmodelが自動的に生成されている
		// ②addAttribute(名前, 内容)
		model.addAttribute("greet_thymeleaf", "Hey! Thymeleaf");
		model.addAttribute("second_message", "タイムリーフ");
		return "helloThymeleaf";
	}
}
