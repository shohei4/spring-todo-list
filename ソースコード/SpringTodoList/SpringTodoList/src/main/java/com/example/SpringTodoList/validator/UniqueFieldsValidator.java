package com.example.SpringTodoList.validator;

import com.example.SpringTodoList.annotation.UniqueFields;
import com.example.SpringTodoList.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

/**
 * ユニーク制約のカスタムバリデーション
 */
@RequiredArgsConstructor
public class UniqueFieldsValidator implements ConstraintValidator<UniqueFields, String> {

    private final UserService userService;
    
    /**
     * バリデーション実行
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	
    	//同じEmailアドレスがないか判定
    	if (userService.existsByUsername(value) > 0) {
    	    // デフォルトメッセージを無効化
    	    context.disableDefaultConstraintViolation();
    	    
    	    // デフォルトメッセージテンプレートを取得してカスタムメッセージを設定
    	    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
    	           .addConstraintViolation();
    	    
    	    // 入力エラー知らせる
    	    return false;
    	}
    	
    	return true;

    }
}

