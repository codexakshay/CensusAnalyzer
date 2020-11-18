package com.blz.codeanalyser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CodeAnalyserTest {

	static CodeAnalyser codeAnalyser;

	private static final String INDIA_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
	private static final String INDIA_CODE_WRONG_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCode.pdf";
	private static final String INDIA_CODE_CSV_WRONG_DELIMITER_FILE_PATH = "./src/test/resources/WrongDelimiterIndiaStateCode.csv";
	private static final String INDIA_CODE_CSV_WITHOUT__HEADER_FILE_PATH = "./src/test/resources/WithoutHeaderIndiaStateCode.csv";

	@BeforeClass
	public static void obj() {
		codeAnalyser = new CodeAnalyser();
	}

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectRecords() {
		try {
			int numOfRecords = codeAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfRecords);
		} catch (CodeAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CodeAnalyserException.class);
			codeAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH);
		} catch (CodeAnalyserException e) {
			Assert.assertEquals(CodeAnalyserException.ExceptionType.CODE_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButIncorrectType_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CodeAnalyserException.class);
			codeAnalyser.loadIndiaStateCodeData(INDIA_CODE_WRONG_FILE_TYPE_PATH);
		} catch (CodeAnalyserException e) {
			Assert.assertEquals(CodeAnalyserException.ExceptionType.WRONG_CODE_FILE_TYPE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButDelimiterIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CodeAnalyserException.class);
			codeAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_WRONG_DELIMITER_FILE_PATH);
		} catch (CodeAnalyserException e) {
			Assert.assertEquals(CodeAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButHeaderIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CodeAnalyserException.class);
			codeAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_WITHOUT__HEADER_FILE_PATH);
		} catch (CodeAnalyserException e) {
			Assert.assertEquals(CodeAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
}
