package com.example.SpringTodoList.form;

import lombok.Data;
/**
 * ログイン認証のフォームクラス
 */
@Data
public class LoginForm {
	/** ユーザー名 */
	private String usernameInput;
	/** パスワード */
	private String passwordInput;
}
