package com.example.SpringTodoList.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.SpringTodoList.entity.User;

/**
 * 認証対象のユーザー情報の実装クラス
 */
public class CustomUserDetails implements UserDetails {

	// アプリ独自のUserエンティティ
	private final User user;
	
	/**
	 * コンストラクタ
	 * @param user
	 */
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	/**
	 * 権限ゲッター
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getAuthority().name()));
	}
	
	/**
	 * パスワードゲッター
	 */
	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getPassword();
	}
	
	/**
	 * emailアドレスゲッター
	 */
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getUsername();
	}

	//表示名のゲッター
	public String getDisplayname() {
		return user.getDisplayname();
	}
	
	public User getUser() {
		return user; // 必要に応じて元のUserオブジェクトを取得できるように
	}

	// UserエンティティからIDを取得するメソッド
	public Integer getId() {
		return user.getId(); // User エンティティの getId() を呼び出す
	}

}
