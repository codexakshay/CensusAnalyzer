package com.blz.codeanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.blz.codeanalyser.CodeAnalyserException;
import com.blz.codeanalyser.IndiaStateCodeCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CodeAnalyser {

	public int loadIndiaStateCodeData(String csvFilePath) throws CodeAnalyserException {
		int namOfEntries = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaStateCodeCSV> censusCSVIterator = csvToBean.iterator();
			while (censusCSVIterator.hasNext()) {
				namOfEntries++;
				IndiaStateCodeCSV codeData = censusCSVIterator.next();
			}
		} catch (NoSuchFileException e) {
			if (!csvFilePath.contains(".csv")) {
			throw new CodeAnalyserException(e.getMessage(),
					CodeAnalyserException.ExceptionType.WRONG_CODE_FILE_TYPE_PROBLEM);
			}
		} catch (IOException e) {
			throw new CodeAnalyserException(e.getMessage(),
					CodeAnalyserException.ExceptionType.CODE_FILE_PROBLEM);
		}catch (RuntimeException e) {
			throw new CodeAnalyserException(e.getMessage(),
					CodeAnalyserException.ExceptionType.INCORRECT_DELIMITER);
		}
		return namOfEntries;
	}
}
