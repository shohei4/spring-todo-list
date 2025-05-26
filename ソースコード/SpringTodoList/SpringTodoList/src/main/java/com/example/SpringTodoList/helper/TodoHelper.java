package com.example.SpringTodoList.helper;

import java.time.LocalDate;

import com.example.SpringTodoList.entity.Todo;
import com.example.SpringTodoList.form.TodoForm;

/**
 * Todo項目変換クラス
 */
public class TodoHelper {
	
	/**
	 * フォームをエンティティに変換
	 * @param form
	 * @return Todoエンティティ
	 */
	public static Todo convertTodo(TodoForm form) {
		
		Todo todo = new Todo();
		todo.setId(form.getId());
		todo.setRegistrationDate(form.getRegistrationDate());
		todo.setExpirationDate(form.getExpirationDate());
		todo.setFinishedDate(form.getIsFinished() ? LocalDate.now() : null);
		todo.setTodoItem(form.getTodoItem());
		todo.setIsDeleted(form.getIsDeleted() != null ? form.getIsDeleted() : 0);
		return todo;
	}

	/**
	 * エンティティをフォームへ変換
	 * @param todo
	 * @return TodoForm
	 */
	public static TodoForm convertTodoForm(Todo todo) {
		
		TodoForm form = new TodoForm();
		form.setId(todo.getId());
		form.setRegistrationDate(todo.getRegistrationDate());
		form.setExpirationDate(todo.getExpirationDate());
		form.setTodoItem(todo.getTodoItem());
		form.setIsDeleted(todo.getIsDeleted());
		return form;
	}

}
