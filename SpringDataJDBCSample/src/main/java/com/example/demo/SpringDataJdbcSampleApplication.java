package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberCrudRepository;

@SpringBootApplication
public class SpringDataJdbcSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcSampleApplication.class, args)
		.getBean(SpringDataJdbcSampleApplication.class).execute();
	}
	
	@Autowired
	MemberCrudRepository repository;
	
	private void execute() {
		executeInsert();
		
		executeSelectAll();
	}
		
	private void executeInsert() {
		Member member = new Member(null, "花子");
		member = repository.save(member);
		System.out.println("登録したデータ" + member);
	}
	
	private void executeSelectAll() {
		System.out.println("メンバー一覧======================");
		Iterable<Member> members = repository.findAll();
		for(Member member : members) {
			System.out.println(member);
		}
	}

}
