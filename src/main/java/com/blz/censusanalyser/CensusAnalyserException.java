package com.blz.censusanalyser;

public class CensusAnalyserException extends Exception {

	enum ExceptionType {
		CENSUS_FILE_PROBLEM,WRONG_CENSUS_FILE_TYPE_PROBLEM, INCORRECT_DELIMITER
	}

	ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}
}
