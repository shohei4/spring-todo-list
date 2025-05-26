package com.example.SpringTodoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.SpringTodoList.security.CustomAuthenticationProvider;
import com.example.SpringTodoList.security.CustomAuthneicationFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	//独自の認証処理クラス
	private final CustomAuthenticationProvider customAuthenticationProvider;
	//独自の認証処理例外クラス
	private final CustomAuthneicationFailureHandler customAuthenticationFailureHandler;

	//SecurityFilterChainのBean定義
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
				//リクエスト処理に関する設定
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/login").permitAll()
						.requestMatchers("/users/**").permitAll()
						.requestMatchers("/webjars/**").permitAll()
						.requestMatchers("/include/**").permitAll()
						// 【管理者権限設定】url:/todos/**は管理者しかアクセスできない
						.requestMatchers("/todos/**").hasAuthority("ADMIN")
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						//ログイン処理のURL指定
						.loginProcessingUrl("/authentication")
						//ユーザー名のname属性を指定
						.usernameParameter("usernameInput")
						//パスワードのname属性を指定
						.passwordParameter("passwordInput")
						//ログイン成功時のリダイレクト先の設定
						.defaultSuccessUrl("/todos", true)
						.permitAll()
						.failureHandler(customAuthenticationFailureHandler) // 失敗時にカスタムハンドラーを指定
				)
				//ログアウトに関する設定
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")//ログアウトがtrueだとメッセージを自動生成
						//ログアウト後にセッションを無効にする
						.invalidateHttpSession(true)
						.clearAuthentication(true) // 認証情報をクリア
						//ログアウト後にクッキーを削除する
						.deleteCookies("JSESSIONID"))
				.authenticationProvider(customAuthenticationProvider);
		return http.build();
	}

}
