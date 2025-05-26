package com.example.SpringTodoList.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 認証に失敗した場合の処理を行うクラス
 */
@Component
public class CustomAuthneicationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		String errorMessage = null;
		
		//例外ごとにメッセージを代入
		if (exception instanceof UsernameNotFoundException) {
			//UsernameNotFoundExceptionが発生したときのエラーメッセージ
			errorMessage = "ユーザーが見つかりません";
		} else if (exception instanceof BadCredentialsException) {
			//BadCredentialsExceptionが発生したときのエラーメッセージ
			errorMessage = "無効なユーザー名かパスワードです";
		} else {
			//その他のエラーが発生したとき
			errorMessage = "ユーザー名かパスワードが間違っています。";
		}

		// URLエンコードを追加！日本語や記号が含まれても大丈夫にする
		String encodedMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);

		// エラーメッセージをクエリパラメータとしてリダイレクト
		response.sendRedirect("/login?error=true&message=" + encodedMessage);
	}

}
