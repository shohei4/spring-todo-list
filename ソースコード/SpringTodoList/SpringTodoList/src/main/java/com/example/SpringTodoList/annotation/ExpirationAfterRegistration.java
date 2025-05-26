package com.example.SpringTodoList.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.SpringTodoList.validator.ExpirationAfterRegistrationValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 期限日が登録日と同じかそれ以降であるかを判定するバリデーションのアノテーションインターフェース
 */
@Target({ ElementType.TYPE }) // クラス全体に適用
@Retention(RetentionPolicy.RUNTIME) // 実行時にリフレクションでアクセス可能
@Constraint(validatedBy = ExpirationAfterRegistrationValidator.class) // バリデーター指定
public @interface ExpirationAfterRegistration {
	
	String message() default "";

	Class<?>[] groups() default {}; // グループ指定

	Class<? extends Payload>[] payload() default {}; // ペイロード指定
}
