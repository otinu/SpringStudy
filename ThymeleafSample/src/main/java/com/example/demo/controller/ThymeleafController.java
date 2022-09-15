package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Member;

@Controller
public class ThymeleafController {

	@GetMapping("show")
	public String showView(Model model) {
		Member member = new Member(1, "会員01");
		model.addAttribute("name", "otinu");
		model.addAttribute("mb", member);
		
		Member member1 = new Member(10, "目近魚");
		Member member2 = new Member(20, "鱚");
		
		List<String> directionList = new ArrayList<>();
		directionList.add("東");
		directionList.add("西");
		directionList.add("南");
		directionList.add("北");
		model.addAttribute("list", directionList);
		
		Map<String, Member> memberMap = new HashMap<>();
		memberMap.put("mejina", member1);
		memberMap.put("kiss", member2);
		model.addAttribute("map", memberMap);
		
		List<Member> memberList = new ArrayList<>();
		memberList.add(member1);
		memberList.add(member2);
		model.addAttribute("members", memberList);
		
		return "useThymeleaf";
	}
	
	@GetMapping("a")
	public String showA() {
		return "pageA";
	}
}
