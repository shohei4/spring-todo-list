package com.example.SpringTodoList.helper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.security.CustomUserDetails;
import com.example.SpringTodoList.service.UserService;

import lombok.RequiredArgsConstructor;


/**
 * 認証されたユーザーのセッション情報を書き換えるためのクラス
 * 現状更新後ログアウトするようにしたため必要なし
 */
@Component
@RequiredArgsConstructor
public class SecurityHelper {

	/** DI */
	private final UserService userService;

	/**
	 * 現在ログイン中のユーザーのIDを取得する
	 * @return ユーザーID（取得できなければnull）
	 */
	public Integer getCurrentUserId() {
			
		//現在のセションにある情報を取得する
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//ログイン中かつ CustomUserDetails の場合、ユーザーIDを取得
		if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {			
			return userDetails.getUser().getId();
		}

		return null; // 未ログイン or 異常時
	}
	
	Integer userId = getCurrentUserId();
	
	/**
	 * セションを維持したまま認証中のユーザー情報を最新化する
	 * @param userId
	 * @param user
	 */
	public void updateAuthentication(Integer userId, User user) {

		//テーブルからUser情報を取得
		user = userService.findByIdUser(userId);
		//最新のログイン情報をテーブル情報を使って取得
		CustomUserDetails userDetails = new CustomUserDetails(user);
		//セションを更新するためのトークン作成
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails,
				userDetails.getPassword(),
				userDetails.getAuthorities());
		//トークンを使ってセション情報を更新
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

}
