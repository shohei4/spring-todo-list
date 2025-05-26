package com.example.SpringTodoList.helper;

import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.form.UserRegisterForm;
import com.example.SpringTodoList.form.UserUpdateForm;

/**
 * ユーザー変換クラス
 */
public class UserHelper {
	
	/**
	 * 登録フォームをエンティティに変換
	 * @param form
	 * @return userエンティティ
	 */
	public static User convertUser(UserRegisterForm form) {

		User user = new User();
		user.setId(form.getId());
		user.setUsername(form.getUsername());
		user.setDisplayname(form.getDisplayname());
		user.setPassword(form.getPassword());
		return user;
	}
	
	/**
	 * エンティティを登録フォームへ変換
	 * @param user
	 * @return 登録フォーム
	 */
	public static UserRegisterForm convertUserRegisterForm(User user) {

		UserRegisterForm form = new UserRegisterForm();
		form.setId(user.getId());
		form.setUsername(user.getUsername());
		form.setDisplayname(user.getDisplayname());
		form.setPassword(user.getPassword());

		return form;
	}
	
	/**
	 * 更新フォームをエンティティに変換
	 * @param form
	 * @return userエンティティ
	 */
	public static User convertUser(UserUpdateForm form) {

		User user = new User();
		user.setId(form.getId());
		user.setUsername(form.getUsername());
		user.setDisplayname(form.getDisplayname());
		user.setPassword(form.getPassword());
		return user;
	}
	
	/**
	 * エンティティを更新フォームに変換
	 * @param user
	 * @return 更新フォーム
	 */
	public static UserUpdateForm convertUserUpdateForm(User user) {
		
		UserUpdateForm form = new UserUpdateForm();
		form.setId(user.getId());
		form.setUsername(user.getUsername());
		form.setDisplayname(user.getDisplayname());
		form.setPassword(user.getPassword());
		return form;

	}

}
