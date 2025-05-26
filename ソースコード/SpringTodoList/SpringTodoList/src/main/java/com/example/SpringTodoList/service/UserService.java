package com.example.SpringTodoList.service;

import java.util.List;

import com.example.SpringTodoList.entity.User;

public interface UserService {
	
	/**
	 * 全件取得
	 * @return User型のリスト
	 */
	List<User> findAllUser();
	
	/**
	 * ユーザー名検索
	 * @param username
	 * @return User型のリスト
	 */
	User findByUsernameUser(String username);
	
	/**
	 * ユーザーid検索
	 * @param id
	 * @return User型のリスト
	 */
	User findByIdUser(Integer id);
	
	/**
	 * ユーザー情報登録
	 * @param user
	 */
	void createUser(User user);
	
	/**
	 * ユーザー情報更新
	 * @param user
	 */
	void editUser(User user);
	
	/**
	 * ユーザー情報削除
	 * @param id
	 */
	void deleteUser(Integer id);
	
	/**
	 * ユーザー名が存在するかの判定
	 * @param username
	 * @return Integer型の値
	 */
	Integer existsByUsername(String username);
}
