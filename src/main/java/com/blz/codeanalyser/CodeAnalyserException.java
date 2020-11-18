package com.blz.codeanalyser;


public class CodeAnalyserException extends Exception {

	enum ExceptionType {
		CODE_FILE_PROBLEM,WRONG_CODE_FILE_TYPE_PROBLEM, INCORRECT_DELIMITER
	}

	ExceptionType type;

	public CodeAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public CodeAnalyserException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}

}
