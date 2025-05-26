package com.example.SpringTodoList.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringTodoList.entity.Todo;
import com.example.SpringTodoList.repository.TodoMapper;
import com.example.SpringTodoList.service.TodoService;

import lombok.RequiredArgsConstructor;

/**
 * Todo項目のサービス実行クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

	/** DI */
	private final TodoMapper todoMapper;

	@Override
	/** 全件取得 */
	public List<Todo> findAllTodo() {
		return todoMapper.findAll();
	}

	@Override
	/** id検索 */
	public Todo findByIdTodo(Integer id) {
		return todoMapper.findById(id);
	}

	@Override
	/** キーワード検索 */
	public List<Todo> findByKeyTodo(String key) {
		return todoMapper.findByKey(key);
	}

	@Override
	/** 登録 */
	public void insertTodo(Todo todo, Integer userId) {
		todoMapper.insert(todo, userId);
	}

	@Override
	/** 更新*/
	public void updateTodo(Todo todo) {
		todoMapper.update(todo);
	}

	@Override
	/** 削除*/
	public void deleteTodo(Integer id) {
		todoMapper.delete(id);
	}

}
