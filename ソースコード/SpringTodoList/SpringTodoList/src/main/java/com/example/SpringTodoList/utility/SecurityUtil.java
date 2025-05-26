package com.example.SpringTodoList.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.SpringTodoList.security.CustomUserDetails;

/**
 * ログイン認証されたユーザー情報をログで表示するクラス
 */
public class SecurityUtil {

	public static void printUserSessionInfo() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			System.out.println("Authenticationがnullです（ログインしていません）");
			return;
		}

		Object principal = auth.getPrincipal();

		System.out.println("principalのクラス: " + principal.getClass().getName());

		if (principal instanceof CustomUserDetails userDetails) {
			System.out.println("ユーザー名: " + userDetails.getUsername());
			System.out.println("権限: " + userDetails.getAuthorities());
			System.out.println("ユーザーID: " + userDetails.getUser().getId());
			System.out.println("ロール: " + userDetails.getUser().getAuthority());
		} else {
			System.out.println("principalはCustomUserDetailsではありません");
			System.out.println("principalの中身: " + principal);
		}
	}

}
