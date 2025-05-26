package com.example.SpringTodoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringTodoList.form.LoginForm;

/**
 * ログインコントローラクラス
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping
	public String showLogin(@ModelAttribute LoginForm form, Model model) {
		
		model.addAttribute("title", "ログイン画面");

		return "login";
	}

}
