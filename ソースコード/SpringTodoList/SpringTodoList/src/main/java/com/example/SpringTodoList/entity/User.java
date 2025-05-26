package com.example.SpringTodoList.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザー情報のエンティティクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	/** ユーザーId */
	private Integer id;
	/** ユーザー名 */
	private String username;
	/**パスワード */
	private String password;
	/** 表示名 */
	private String displayname;
	/** 権限　*/
	private Role authority;
	/** 作成日 */
	private LocalDateTime createdAt;
	/** 更新日 */
	private LocalDateTime updatedAt;
}
