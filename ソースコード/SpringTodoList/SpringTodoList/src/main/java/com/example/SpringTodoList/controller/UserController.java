package com.example.SpringTodoList.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.form.UserRegisterForm;
import com.example.SpringTodoList.form.UserUpdateForm;
import com.example.SpringTodoList.helper.UserHelper;
import com.example.SpringTodoList.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * ユーザー情報のコントローラークラス
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	/** DI */
	private final UserService userService;
	private final BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("title")
	public String title(HttpServletRequest request) {
		
		String path = request.getRequestURI();

		if (path.contains("users/form"))
			return "ユーザー情報登録画面";
		if (path.contains("users/save"))
			return "ユーザー情報登録更新画面";
		if (path.contains("users/edit"))
			return "ユーザー情報更新画面";
		if (path.contains("users/update"))
			return "ユーザー情報更新画面";

		return "ユーザー";
	}

	@GetMapping
	public String showList() {
		
		return "redirect:/todos";
	}

	@GetMapping("/form")
	public String showForm(@ModelAttribute UserRegisterForm form) {
		
		return "user/userRegister";
	}

	@PostMapping("/save")
	public String save(@Validated UserRegisterForm form, BindingResult bindingResult, RedirectAttributes attributes,
			Model model) {

		//バリデーションチェック
		if (bindingResult.hasErrors()) {		
			//htmlタイトル
			model.addAttribute("title", "ユーザー情報登録画面");

			return "user/userRegister";
		}
		//パスワードのハッシュ化
		String hashedEncoder = passwordEncoder.encode(form.getPassword());
		form.setPassword(hashedEncoder);
		
		//fromクラスをEntityクラスへ変換
		User user = UserHelper.convertUser(form);

		//登録処理
		userService.createUser(user);
		
		//リダイレクト先で表示するメッセージ
		attributes.addFlashAttribute("message", "登録しました");

		return "redirect:/login";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		
		//id検索
		User target = userService.findByIdUser(id);
		//targetのnullチェック
		if (target != null) {			
			//formへ変換しModelに入れる
			UserUpdateForm form = UserHelper.convertUserUpdateForm(target);
			model.addAttribute("userUpdateForm", form);
			//htmlタイトル
			model.addAttribute("title", "ユーザー情報更新画面");

			return "user/userUpdate";
			
		} else {			
			//対象データがない場合はフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			//メニュー画面へリダイレクト
			return "redirect:/todos";
		}
	}

	@PostMapping("/update")
	public String update(@Validated UserUpdateForm form,
			BindingResult bindingResult, RedirectAttributes attributes, Model model, 
			HttpServletRequest request) {
		
		//バリデーションチェック
		if (bindingResult.hasErrors()) {			
			//htmlタイトル
			model.addAttribute("title", "ユーザー情報更新画面");
			return "user/userUpdate";
		}
		//パスワードのハッシュ化しモデルにセット
		String hashedEncoder = passwordEncoder.encode(form.getPassword());
		form.setPassword(hashedEncoder);
		//エンティティに変換後更新処理
		User user = UserHelper.convertUser(form);
		userService.editUser(user);

		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "Todo項目を更新しました");
		//メニュー画面へリダイレクト
		try {		
			request.logout(); // 実際にログアウト
		} catch (ServletException e) {
			//例外がある場合ログ表示
			e.printStackTrace();
		}
		return "redirect:/login?logout";

	}
}
