package com.example.quiz.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.quiz.QuizForm;
import com.example.quiz.entity.Quiz;
import com.example.quiz.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService service;

	@ModelAttribute
	public QuizForm setUpForm() {
		QuizForm form = new QuizForm();
		form.setAnswer(true);
		return form;
	}

	@GetMapping
	public String showList(QuizForm quizForm, Model model) {
		quizForm.setNewQuiz(true);
		Iterable<Quiz> list = service.selectAll();
		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "crud";
	}

	@PostMapping("/insert")
	public String insert(@Validated QuizForm quizForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		Quiz quiz = new Quiz();
		quiz.setQuestion(quizForm.getQuestion());
		quiz.setAnswer(quizForm.getAnswer());
		quiz.setAuthor(quizForm.getAuthor());

		if (!bindingResult.hasErrors()) {
			service.insertQuiz(quiz);

			/*
			 * ①redirectAttributesを使うことで、リダイレクト先に値を渡すことができる
			 * 
			 * ②addFlashAttributeによって、Flashスコープに値を渡している
			 * ⇒Flashスコープは1回目のリダイレクトだけがスコープ範囲
			 */
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/quiz";
		} else {
			return showList(quizForm, model);
		}

	}

	@GetMapping("/{id}")
	public String showUpdate(QuizForm quizForm, @PathVariable Integer id, Model model) {
		Optional<Quiz> quizOpt = service.selectOneById(id);
		Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));

		if (quizFormOpt.isPresent()) {
			quizForm = quizFormOpt.get();
		}

		makeUpdateModel(quizForm, model);
		return "crud";
	}

	private void makeUpdateModel(QuizForm quizForm, Model model) {
		model.addAttribute("id", quizForm.getId());
		quizForm.setNewQuiz(false); // エンティティQuizのフィールドnewQuizのSetter
		model.addAttribute("quizForm", quizForm);
		model.addAttribute("title", "更新用フォーム");
	}

	@PostMapping("/update")
	public String update(@Validated QuizForm quizForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		Quiz quiz = makeQuiz(quizForm);
		if (!result.hasErrors()) {
			service.updateQuiz(quiz);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました");
			return "redirect:/quiz/" + quiz.getId();
		} else {
			makeUpdateModel(quizForm, model);
			return "crud";
		}
	}

	/** QuizFormからQuizに詰め直して戻り値とし返します */
	private Quiz makeQuiz(QuizForm quizForm) {
		Quiz quiz = new Quiz();
		quiz.setId(quizForm.getId());
		quiz.setQuestion(quizForm.getQuestion());
		quiz.setAnswer(quizForm.getAnswer());
		quiz.setAuthor(quizForm.getAuthor());
		return quiz;
	}

	/** QuizからQuizFormに詰め直して戻り値とし返します */
	private QuizForm makeQuizForm(Quiz quiz) {
		QuizForm form = new QuizForm();
		form.setId(quiz.getId());
		form.setQuestion(quiz.getQuestion());
		form.setAnswer(quiz.getAnswer());
		form.setAuthor(quiz.getAuthor());
		form.setNewQuiz(false);
		return form;
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.deleteQuizById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/quiz";
	}
	
	@GetMapping("/play")
	public String showQuiz(QuizForm quizForm, Model model) {
		Optional<Quiz> quizOpt = service.selectOneRandom();
		if(quizOpt.isPresent()) {
			Optional<QuizForm> quizFormOpt = quizOpt.map(t -> makeQuizForm(t));
			quizForm = quizFormOpt.get();
		} else {
			model.addAttribute("msg", "クイズが見つかりません");
			return "play";
		}
		
		model.addAttribute("quizForm", quizForm);
		return "play";
	}
	
	@PostMapping("/check")
	public String checkQuiz(QuizForm quizForm, @RequestParam Boolean answer, Model model) {
		if(service.checkQuiz(quizForm.getId(), answer)) {
			model.addAttribute("msg", "〇");
		} else {
			model.addAttribute("msg", "×");
		}
		
		return "answer";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}