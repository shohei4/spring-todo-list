package com.example.SpringTodoList.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.repository.UserMapper;
import com.example.SpringTodoList.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * ユーザーのサービス実行クラス
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	/** DI */
	private final UserMapper mapper;
	
	/**
	 * 全件取得
	 */
	@Override
	public List<User> findAllUser() {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.selectAll();
	}
	
	/**
	 * Emailアドレス検索
	 */
	@Override
	public User findByUsernameUser(String username) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.selectByUsername(username);
	}
	
	/**
	 * id検索
	 */
	@Override
	public User findByIdUser(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.selectById(id);
	}
	
	/**
	 * ユーザー登録
	 */
	@Override
	public void createUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.insert(user);
	}
	
	/**
	 * ユーザー更新
	 */
	@Override
	public void editUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.update(user);
	}	
	
	/**
	 * ユーザー削除	
	 */
	@Override
	public void deleteUser(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.delete(id);
	}
	
	/**
	 * Emailアドレスの重複チェック
	 */
	@Override
	public Integer existsByUsername(String username) {
		// 入力されたユーザー名が存在するかのチェック
		Integer count = mapper.existsByUsername(username);
		return count;
	}

}
