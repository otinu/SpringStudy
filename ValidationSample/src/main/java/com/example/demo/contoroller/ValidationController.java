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
	// @InitBinderにはチェック対象Formクラスのモデルでの識別名(ロワーキャメルケース)
	@InitBinder("calcForm")
	public void initBinder(WebDataBinder webDataBinder) {
		
		// これで、Viewファイル側で「th:object="${〇〇}"」の形式でFormクラスが使えるようになる
		// ⇒entry.html で使用
		webDataBinder.addValidators(calcValidator);
	}
	
	// バリデーションを使うには、@ModelAttribute の設置が必要
	@ModelAttribute
	public CalcForm setUpForm() {
		/*
		 * @ModelAttributeがついたこのメソッドは、リクエストハンドラメソッドが呼び出される前に実行され、
		 * リクエストスコープでModelに自動的に設定される
		*/
		
		// 戻り値の型がバリデーションの対象クラス(form-backing beanという)になる
		return new CalcForm();
	}
	
	@GetMapping("show")
	public String showView() {
		return "entry";
	}
	
	@PostMapping("calc")
	
	/*
	 * @Validatedで「バリデーションを実行する」サインになり、実行結果はBindingResultに保持される。
	 * この二つはバリデーションをする際に必ず渡す必要があり、順番も「@Validated ⇒ BindingResult」になる
	 */
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
