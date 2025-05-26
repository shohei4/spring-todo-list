package com.example.SpringTodoList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringTodoList.entity.User;

/**
 * User：リポジトリ
 */
@Mapper
public interface UserMapper {

	/**
	 * ユーザー名でユーザー情報を検索
	 */
	User selectByUsername(String username);

	/**
	 * 認証情報をすべて取得
	 * @return
	 */
	List<User> selectAll();

	/**
	 * 認証情報をid検索
	 * @param id
	 * @return
	 */
	User selectById(Integer id);

	/**
	 * 認証情報を追加
	 * @param authetication
	 * @return
	 */
	void insert(User user);

	/**
	 * 認証情報を更新
	 * @param authentication
	 */
	void update(User user);

	/**
	 * 認証情報を削除
	 */
	void delete(Integer id);

	/**
	 * 入力されたユーザー名があるかの判定
	 */
	Integer existsByUsername(String username);
}
