package com.example.SpringTodoList.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * todoItemのentity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	/** ID */
	private Integer id;

	/** ユーザーID */
	private Integer userId;

	/** ユーザー名 */
	private String displayname;

	/** 登録日 */
	private LocalDate registrationDate;

	/** 期限日 */
	private LocalDate expirationDate;

	/** 終了日 */
	private LocalDate finishedDate;

	/** Todo項目 */
	private String todoItem;

	/** 削除フラグ */
	private Integer isDeleted;

	/** 作成日 */
	private LocalDateTime createdAt;

	/** 更新日 */
	private LocalDateTime updatedAt;
}