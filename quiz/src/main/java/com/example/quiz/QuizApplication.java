
package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args)
		.getBean(QuizApplication.class).execute();
	}
	
	@Autowired
	QuizService service;
	
	private void execute() {
		//setup();
		//showList();
		//showOne();
		//updateQuiz();
		//deleteQuiz();
		doQuiz();
	}
	
	private void setup() {
		System.out.println("---登録開始---");
		Quiz quiz1 = new Quiz(null, "「Spring」はフレームワークですか？", true, "登録太郎");
		Quiz quiz2 = new Quiz(null, "「SpringMVC」はバッチ処理機能を提供しますか？", false, "登録太");
		Quiz quiz3 = new Quiz(null, "コマセに重要な要素には匂いと味、色の他にもある", true, "otinu");
		Quiz quiz4 = new Quiz(null, "フカセで黒鯛を狙うなら、ロッドは3号以上にするべき", false, "otinu");
		Quiz quiz5 = new Quiz(null, "フカセでガン玉を使わない戦略もある", true, "otinu");
		
		List<Quiz> quizList = new ArrayList<>();
		Collections.addAll(quizList, quiz1, quiz2, quiz3, quiz4, quiz5);
		for(Quiz quiz : quizList) {
			service.insertQuiz(quiz);
		}
		System.out.println("---終了---");
	}
	
	private void showList() {
		System.out.println("---全件取得します---");
		
		Iterable<Quiz> quizzes = service.selectAll();
		for(Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		System.out.println("---終了---");
	}
	
	private void showOne() {
		System.out.println("---1件取得---");
		Optional<Quiz> quizOpt = service.selectOneById(10);
		if(quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当データなし");
		}
		System.out.println("---終了---");
	}
	
	private void updateQuiz() {
		System.out.println("---更新処理開始---");
		Quiz quiz1 = new Quiz(31, "質問の変更", true, "otinu");
		service.updateQuiz(quiz1);
		System.out.println(quiz1 + "を更新");
		System.out.println("---終了---");
	}
	
	private void deleteQuiz() {
		System.out.println("---削除開始---");
		service.deleteQuizById(30);
		System.out.println("---終了---");
	}
	
	private void doQuiz() {
		System.out.println("---クイズを1件取得---");
		Optional<Quiz> quizOpt = service.selectOneRandom();
		
		if(quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		} else {
			System.out.println("該当のクイズが見つかりません");
		}
		System.out.println("---クイズ1件取得---");
		
		Boolean myAnswer = false;
		Integer id = quizOpt.get().getId();
		if(service.checkQuiz(id, myAnswer)) {
			System.out.println("〇");
		} else {
			System.out.println("×");
		}
	}
	
	
	
/*
package com. example. quiz; 

import org. springframework. boot. SpringApplication; 
import org. springframework. boot. autoconfigure. SpringBootApplication; 

@SpringBootApplication 
public class QuizApplication { 

public static void main( String[] args) { 
	SpringApplication. run( QuizApplication. class, args);
	}
}
*/
	
	
	
	
	
	
	
	
	
	
	
	
	

}
