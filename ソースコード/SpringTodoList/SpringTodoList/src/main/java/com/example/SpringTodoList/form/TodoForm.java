package com.example.SpringTodoList.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.SpringTodoList.annotation.ExpirationAfterRegistration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Todo項目のフォームクラス
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExpirationAfterRegistration
public class TodoForm {

	/** id */
	private Integer id;

	/** 登録日 */
	@NotNull(message = "登録日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd") // フォームの日付フォーマットを指定
	private LocalDate registrationDate;

	/** 期限日　*/
	@NotNull(message = "期限日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd") // フォームの日付フォーマットを指定
	private LocalDate expirationDate;

	/** 終了日　*/
	private Boolean isFinished = false;

	/** Todo項目 */
	@NotBlank(message = "todo項目は必須です")
	@Size(min = 1, max = 50, message = "todo項目は{min}文字以上～{max}文字以内で入力してください")
	private String todoItem;

	/** 削除フラグ */
	private Integer isDeleted;
	
	/** 新規判定 */
	private Boolean isNew = false;

}
