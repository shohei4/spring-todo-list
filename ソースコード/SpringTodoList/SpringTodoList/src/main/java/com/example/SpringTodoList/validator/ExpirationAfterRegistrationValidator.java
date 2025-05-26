package com.example.SpringTodoList.validator;

import com.example.SpringTodoList.annotation.ExpirationAfterRegistration;
import com.example.SpringTodoList.form.TodoForm;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 期限日のカスタムバリデーション実装クラス
 */
public class ExpirationAfterRegistrationValidator
		implements ConstraintValidator<ExpirationAfterRegistration, TodoForm> {
	
	/**
	 * バリデーション実行メソッド
	 */
	@Override
	public boolean isValid(TodoForm todoForm, ConstraintValidatorContext context) {
		
		//登録日と期限日がnullでなく、期限日が登録日より前であるかを判定している
		if (todoForm.getRegistrationDate() != null && todoForm.getExpirationDate() != null) {
			if (todoForm.getExpirationDate().isBefore(todoForm.getRegistrationDate())) {
				
				context.disableDefaultConstraintViolation();  // デフォルトメッセージを無効化
                context.buildConstraintViolationWithTemplate("期限日は登録日と同じか、それより後の日付にしてください")
                       .addPropertyNode("expirationDate")  // エラーを `expirationDate` に紐付け
                       .addConstraintViolation();  // エラーを追加
                
	            return false;
	        }
			
		}
		return true; // nullの場合はバリデーションを通す
	}
}
