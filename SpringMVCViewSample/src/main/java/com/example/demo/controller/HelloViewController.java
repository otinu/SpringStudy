package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

// 引数にvalueとmethodを指定する。
// methodは省略可能。デフォルトはGetリクエストになる
// valueでURLを決める。「hello」とすれば、URLは「http://…/hello」となる
@RequestMapping("hello")
public class HelloViewController {

	// 引数にはvalueのみを指定する。
	// 複数指定も可能で、
	//「GETリクエストの htp://～～/hello/1」や「GETリクエストの htp://～～/hello/2」を作成できる
	@GetMapping("view")
	
	// ここで対応するURLは「http://～～/hello/view」になる
	// ⇒呼び出されるメソッドは「helloView」になる
	
	public String helloView() {
		String phrase = "hello";
		return phrase;
	}
}
