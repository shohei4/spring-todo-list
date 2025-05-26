package com.example.SpringTodoList.validator;

import com.example.SpringTodoList.annotation.UpdateUniqueFields;
import com.example.SpringTodoList.entity.User;
import com.example.SpringTodoList.helper.SecurityHelper;
import com.example.SpringTodoList.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

/**
 *更新時のユニーク制約のカスタムバリデーション
 */
@RequiredArgsConstructor
public class UpdateUniqueFieldsValidator implements ConstraintValidator<UpdateUniqueFields, String> {

	/** DI */
	private final UserService userService;
	private final SecurityHelper securityHelper;
	
	@Override
	public void initialize(UpdateUniqueFields constraintAnnotation) {
		// 初期化処理（必要であれば）
	}
	
	/**
	 * バリデーション実行
	 */
	@Override
    public boolean isValid(String newUsername, ConstraintValidatorContext context) {
		
    	//真偽を保管する変数
    	boolean exists;
    	//現在登録中のemailと更新時にformで受け取った入力値をそれぞれ代入
    	int userId = securityHelper.getCurrentUserId();
    	User user = userService.findByIdUser(userId);
    	String currentUsername = user.getUsername();
    		if (newUsername.equals(currentUsername)) {
    			// 更新前のusernameと変わっていない場合はチェックしない
    			return true;
    		}
        // 新しいusernameが既に存在するかチェック
        exists = userService.existsByUsername(newUsername) > 0;
        return !exists;
    }

	
}
