package com.blz.censusanalyser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String INDIA_CENSUS_WRONG_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.pdf";
	private static final String INDIA_CENSUS_CSV_WRONG_DELIMITER_FILE_PATH = "./src/test/resources/WrongDelimiterIndiaStateCensusData.csv";
	private static final String INDIA_CENSUS_CSV_WITHOUT__HEADER_FILE_PATH = "./src/test/resources/WithoutHeaderIndiaStateCensusData.csv";
	private static final String INDIA_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String INDIA_CODE_WRONG_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCode.pdf";
	private static final String INDIA_CODE_CSV_WRONG_DELIMITER_FILE_PATH = "./src/test/resources/WrongDelimiterIndiaStateCode.csv";
	private static final String INDIA_CODE_CSV_WITHOUT__HEADER_FILE_PATH = "./src/test/resources/WithoutHeaderIndiaStateCode.csv";

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
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButDelimiterIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WRONG_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectFileButHeaderIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_WITHOUT__HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianStateCodeCSVFile_ReturnsCorrectRecords() {
		try {
			int numOfRecords = censusAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithWrongFile_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithCorrectFileButIncorrectType_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(INDIA_CODE_WRONG_FILE_TYPE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithCorrectFileButDelimiterIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_WRONG_DELIMITER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeData_WithCorrectFileButHeaderIncorrect_ShouldThrowException() {
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCodeData(INDIA_CODE_CSV_WITHOUT__HEADER_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	// UC3
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnResult() {
		String sortedCensusData = null;
		try {
			sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC4
	public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnResult() {
		String sortedCensusData = null;
		try {
			sortedCensusData = censusAnalyser.getStateCodeSortedIndiaStateCodedata(INDIA_CODE_CSV_FILE_PATH);
			IndiaStateCodeCSV[] codeCSV = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
			Assert.assertEquals("AP", codeCSV[0].stateCode);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	// UC5
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
		String sortedCensusData = null;
		try {
			sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(199812341, censusCSV[censusCSV.length - 1].population);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	//UC6
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDesnity_ShouldReturnSortedResult() {
		String sortedCensusData = null;
		try {
			sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(1102, censusCSV[censusCSV.length - 1].densityPerSqKm);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
}
