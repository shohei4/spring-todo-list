package com.example.SpringTodoList.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.SpringTodoList.validator.UpdateUniqueFieldsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// 更新用のユニーク制約に反しないかを判定するバリデーションのアノテーションインターフェース
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UpdateUniqueFieldsValidator.class) // バリデータクラス指定
public @interface UpdateUniqueFields {
	
	String message() default ""; // デフォルトのエラーメッセージ

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String field(); // ← アノテーションの「属性（パラメータ）」を宣言しているメソッド！
}
