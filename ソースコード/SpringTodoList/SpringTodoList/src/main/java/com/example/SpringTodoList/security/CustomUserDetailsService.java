package com.example.SpringTodoList.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.repository.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * 認証処理を行うクラス
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserMapper userMapper;
	
	/**
	 * 認証処理メソッド
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		//MyBatisを使用してユーザー情報
		User user = userMapper.selectByUsername(username);

		//nullの場合の例外処理
		if (user == null) {
			throw new UsernameNotFoundException("ユーザーが見つかりません");
		}

		//null出ない場合UserDetailsを返す
		return new CustomUserDetails(user);

	}
}
