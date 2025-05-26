package com.example.SpringTodoList.service;

import java.util.List;

import com.example.SpringTodoList.entity.Todo;

public interface TodoService {

	/**
	 * Todo項目を全件取得
	 */
	List<Todo> findAllTodo();

	/**
	 * Todo項目をIDを指定して取得
	 */
	Todo findByIdTodo(Integer id);

	/**
	 * 特定のTodo項目をキーワードで検索
	 */
	List<Todo> findByKeyTodo(String key);

	/**
	 * Todo項目を1件追加
	 */
	void insertTodo(Todo todo, Integer userId);

	/**
	 * Todo項目を１件更新
	 */
	void updateTodo(Todo todo);

	/**
	 * Todo項目を１件削除
	 */
	void deleteTodo(Integer id);
}
