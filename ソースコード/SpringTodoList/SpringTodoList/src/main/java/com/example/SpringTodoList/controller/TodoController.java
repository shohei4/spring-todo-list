package com.example.SpringTodoList.controller;

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

import com.example.SpringTodoList.entity.Todo;
import com.example.SpringTodoList.form.TodoForm;
import com.example.SpringTodoList.helper.TodoHelper;
import com.example.SpringTodoList.service.TodoService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Todo項目のコントローラークラス
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

	/** DI */
	private final TodoService todoService;

	//ページタイトルオブジェクト
	@ModelAttribute("title")
	public String title(HttpServletRequest request) {

		String path = request.getRequestURI();

		//フォワード先ごとに条件分岐
		if (path.contains("todos/list"))
			return "Todo一覧";
		if (path.contains("todos/form"))
			return "Todo項目登録画面";
		if (path.contains("todos/save"))
			return "Todo項目登録画面";
		if (path.contains("todos/edit"))
			return "Todo項目更新画面";
		if (path.contains("todos/update"))
			return "Todo項目更新画面";
		return "Todo一覧";
	}

	//一覧表示
	@GetMapping
	public String list(Model model) {
		model.addAttribute("todos", todoService.findAllTodo());
		return "todo/list";
	}

	@GetMapping("/key")
	public String ByKeylist(Model model, @RequestParam String key) {

		//キーワードが入力されなかった場合全件取得する
		if (key == null || key.trim().isEmpty()) {	
			model.addAttribute("todos", todoService.findAllTodo());

			return "todo/list";
		}
		//キーワード検索結果をモデルに入れる
		model.addAttribute("todos", todoService.findByKeyTodo(key));

		return "todo/list";
	}

	@GetMapping("/form")
	public String form(@ModelAttribute TodoForm form) {
		
		//登録用画面を表示するための新規判定
		form.setIsNew(true);

		return "todo/form";
	}

	@PostMapping("/save/{userId}")
	public String save(@Validated TodoForm form, BindingResult bindingResult, @PathVariable Integer userId,
			RedirectAttributes attributes, Model model) {
		
		//バリデーションチェック
		if (bindingResult.hasErrors()) {			
			form.setIsNew(true);
			//htmlタイトル
			model.addAttribute("title", "Todo項目登録画面");
			return "todo/form";
		}
		
		//エンティティへの変換
		Todo todo = TodoHelper.convertTodo(form);
		//登録実行
		todoService.insertTodo(todo, userId);
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "Todo項目を登録しました");

		return "redirect:/todos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		
		//id検索
		Todo target = todoService.findByIdTodo(id);
		if (target != null) {			
			//対象データがある場合はFormへの変換
			TodoForm form = TodoHelper.convertTodoForm(target);
			//完了日のnullチェック　nullでなければtrueをformに設定
			if (target.getFinishedDate() != null) {				
				form.setIsFinished(true);
			}
			//モデルに格納
			model.addAttribute("todoForm", form);
			return "todo/form";

		} else {
			//対象データがない場合はフラッシュメッセージを設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");

			//一覧画面へリダイレクト
			return "redirect:/todos";
		}
	}

	@PostMapping("/update")
	public String edit(@Validated TodoForm form, BindingResult bindingResult, RedirectAttributes attributes,
			Model model) {
		
		//バリデーションチェック
		if (bindingResult.hasErrors()) {			
			model.addAttribute("title", "Todo項目更新画面");
			return "todo/form";
		}
		//エンティティ変換
		Todo todo = TodoHelper.convertTodo(form);

		//更新実行
		todoService.updateTodo(todo);
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "Todo項目を更新しました");
		return "redirect:/todos";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
		
		//削除実行
		todoService.deleteTodo(id);
		//フラッシュメッセージ
		attributes.addFlashAttribute("message", "削除に成功しました");
		return "redirect:/todos";
	}

}
