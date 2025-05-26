package com.example.SpringTodoList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.SpringTodoList.entity.Todo;

/**
 * Todo：リポジトリ
 */
@Mapper
public interface TodoMapper {

	/**
	 * すべてのtodo項目を取得
	 * @return
	 */
	List<Todo> findAll();

	/**
	 * 指定されたIDのtodo項目を取得
	 */
	Todo findById(Integer id);

	/**
	 * 特定のTodo項目をキーワード検索
	 * @param key
	 * @return
	 */
	List<Todo> findByKey(@Param("key") String key);

	/**
	 * Todo項目を1件追加する
	 */
	void insert(@Param("todo") Todo todo, @Param("userId") Integer userId);

	/**
	 * Todo項目を１件更新する
	 */
	void update(Todo todo);

	/**
	 * Todo項目を１件削除
	 */
	void delete(@Param("id") Integer id);

}
