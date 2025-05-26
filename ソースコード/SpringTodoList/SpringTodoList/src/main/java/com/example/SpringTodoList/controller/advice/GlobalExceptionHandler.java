package com.example.SpringTodoList.controller.advice;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例外処理を行うクラス
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// データベース関連の例外
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public ModelAndView handleDatabaseExceptions(Exception ex, WebRequest request) {
		
		// ログ出力
		logger.error("【DB例外】パス: {} - エラー内容: {}", request.getDescription(false), ex.getMessage(), ex);
		
		//forwad先とメッセージをモデルに入れる
		ModelAndView mav = new ModelAndView("error/500");
		mav.addObject("message", "予期しないエラーが発生しました。");
		return mav;
	}

	// NullPointerExceptionなど
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(NullPointerException ex, WebRequest request) {
		
		logger.warn("【NullPointerException】パス: {} - 詳細: {}", request.getDescription(false), ex.getMessage(), ex);
		
		//forwad先とメッセージをモデルに入れる
		ModelAndView mav = new ModelAndView("error/null");
		mav.addObject("message", "予期しないエラーが発生しました。");
		return mav;
	}

	// その他の予期しない例外
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception ex, WebRequest request) {
		
		logger.error("【一般例外】パス: {} - エラー: {}", request.getDescription(false), ex.getMessage(), ex);
		
		//forwad先とメッセージをモデルに入れる
		ModelAndView mav = new ModelAndView("error/500");
		mav.addObject("message", "予期しないエラーが発生しました。");
		return mav;
	}
}
