package com.example.demo.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.CalcForm;
import com.example.demo.validator.CalcValidator;

@Controller
public class ValidationController {
	
	@Autowired
	CalcValidator calcValidator;
	
	// 相関チェックの登録
	// @InitBinderにはチェック対象Formクラスのモデルでの識別名(キャメルケース)
	@InitBinder("calcForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(calcValidator);
	}
	
	// HTMLのformタグにバインドする「Formクラス」インスタンスの初期化
	// ⇒「form-backing bean」 という
	@ModelAttribute // form-backing beanは@ModelAttribute で初期化を行う
	public CalcForm setUpForm() {
		/*
		@ModelAttributeがついたこのメソッドは、リクエストハンドラメソッド(ここではshowView())
		が呼び出される前に「リクエストスコープ」でModelに格納される。
		*/
		return new CalcForm();
	}
	
	@GetMapping("show")
	public String showView() {
		return "entry";
	}
	
	@PostMapping("calc")
	
	// @Validatedで「バリデーションを実行する」サインになり、実行結果はBindingResultに保持される。
	// この二つはバリデーションをする際に必ず渡す必要があり、順番も「@Validated ⇒ BindingResult」になる
	public String confirmView(@Validated CalcForm form,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "entry";
		}
		
		Integer result = form.getLeftNum() + form.getRightNum();
		model.addAttribute("result", result);
		
		return "confirm";
	}
}
