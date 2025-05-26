package com.example.SpringTodoList.form;

import com.example.SpringTodoList.annotation.UniqueFields;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 登録用のユーザーフォームクラス
 */
@Data
public class UserRegisterForm {
	/** ユーザーid */
	private Integer id;

	/** ユーザー名 */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "有効なメールアドレスを入力してください")
	@UniqueFields(field = "email", message = "このメールアドレスは既に使用されています")
	private String username;

	/** パスワード */
	@Size(min = 1, max = 20, message = "パスワードは{min}文字以上～{max}文字以内で入力してください")
	private String password;

	/** 表示名 */
	@Size(min = 1, max = 50, message = "表示名は{min}文字以上～{max}文字以内で入力してください")
	private String displayname;

}
