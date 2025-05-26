package com.example.SpringTodoList.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.SpringTodoList.validator.UniqueFieldsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ユニーク制約に反しないか判定するバリデーションのアノテーションインターフェース
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldsValidator.class)
public @interface UniqueFields {
	
	String message() default "";

	Class<?>[] groups() default {}; // 複数のバリデーショングループを指定するための配列

	Class<? extends Payload>[] payload() default {}; // カスタムペイロードを指定するための配列

	String field(); // ← アノテーションの「属性（パラメータ）」を宣言しているメソッド！
}
