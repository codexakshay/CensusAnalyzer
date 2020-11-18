package com.blz.censusanalyser;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String INDIA_CENSUS_WRONG_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.pdf";
	private static final String INDIA_CENSUS_CSV_WRONG_DELIMITER_FILE_PATH = "./src/test/resources/WrongDelimiterIndiaStateCensusData.csv";
	private static final String INDIA_CENSUS_CSV_WITHOUT__HEADER_FILE_PATH = "./src/test/resources/WithoutHeaderIndiaStateCensusData.csv";
	static CensusAnalyser censusAnalyser;

	@BeforeClass
	public static void obj() {
		censusAnalyser = new CensusAnalyser();
	}

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectRecords() {
		try {
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButIncorrectType_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_WRONG_FILE_TYPE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_CENSUS_FILE_TYPE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButDelimiterIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButHeaderIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WITHOUT__HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}

}
