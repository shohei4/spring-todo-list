package com.example.SpringTodoList.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/**
 * 認証情報を返す処理を行うAuthenticationProviderクラスの実装クラス
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/** DI */
	private final CustomUserDetailsService userDetailsService;
	private final PasswordEncoder passwordConfig;

	//認証情報をもとに認証処理を行い、認証されたオブジェクトを保持する
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		//平文のパスワードとハッシュ化されたパスワードを比較するために使用されます。
		//パスワードが一致すれば true を返し、認証が成功します。
		if (passwordConfig.matches(rawPassword, userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(
					userDetails, rawPassword, userDetails.getAuthorities());
		} else {
			//この例外は、Spring Security によって認証エラーとして処理されます。
			throw new BadCredentialsException("Invalid username or password");
		}

	}
	
	@Override
	//このsupportメソッドにより、CustomAuthenticationProvider は
	//UsernamePasswordAuthenticationToken による認証のみをサポートすることになります。
	public boolean supports(Class<?> authentication) {
		// TODO 自動生成されたメソッド・スタブ
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

	}

}
