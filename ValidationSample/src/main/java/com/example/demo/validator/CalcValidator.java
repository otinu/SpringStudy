package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.form.CalcForm;

@Component
public class CalcValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		// 渡されたFormが入力チェックの対象かを論理値で返す
		// 今回の場合、「渡されたFormがCalcFormならtrueを返す」
		return CalcForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		CalcForm form = (CalcForm)target;
		if(form.getLeftNum() != null&&form.getRightNum() != null) {
			if(!((form.getLeftNum() % 2 == 1) && (form.getRightNum() % 2 == 0))) {
				
				// エラーメッセージのキーを指定
				errors.reject("com.example.demo.validator.CalcValidator.message");
			}
		}
	}
}

/*
 * Q.相関チェックの実装で何が変わった？ 何のメリットがある？
 * 
 * A.
 * 　①画面側 + 【コントローラ側】でもバリデーションがかけられる
 * 　②バリデーション用のアノテーションに頼らないことで、自分好みにカスタマイズしたバリデーションが扱える
 * 
 */