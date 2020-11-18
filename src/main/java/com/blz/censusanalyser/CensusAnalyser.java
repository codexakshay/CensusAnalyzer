package com.blz.censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

public class CensusAnalyser {

	List<IndianCensusDAO> csvFileList;
	List<IndiaStateCodeCSV> stateFileList = new ArrayList<IndiaStateCodeCSV>();

	public CensusAnalyser() {
		this.csvFileList = new ArrayList<IndianCensusDAO>();
	}

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilderFactory csvBuilder = CSVBuilderFactory.createCsvBuilder();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
			while (censusCSVIterator.hasNext()) {
				this.csvFileList.add(new IndianCensusDAO(censusCSVIterator.next()));
			}
			return csvFileList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		} catch (NullPointerException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilderFactory csvBuilder = CSVBuilderFactory.createCsvBuilder();
			List<IndiaStateCodeCSV> csvFileList = csvBuilder.getCSVFileList(reader, IndiaStateCodeCSV.class);
			return csvFileList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int noOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return noOfEntries;
	}

	public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
		loadIndiaCensusData(csvFilePath);
		if (csvFileList == null || csvFileList.size() == 0)
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndianCensusDAO> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		return new Gson().toJson(this.csvFileList);
	}
	
	public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
		loadIndiaCensusData(csvFilePath);
		if (csvFileList == null || csvFileList.size() == 0)
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndianCensusDAO> censusComparator = Comparator.comparing(census -> census.population);
		this.sort(censusComparator);
		return new Gson().toJson(this.csvFileList);
	}
	
	public String getPopulationDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
		loadIndiaCensusData(csvFilePath);
		if (csvFileList == null || csvFileList.size() == 0)
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndianCensusDAO> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
		this.sort(censusComparator);
		return new Gson().toJson(this.csvFileList);
	}
	
	public String getAreaPerSqKmWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
		loadIndiaCensusData(csvFilePath);
		if (csvFileList == null || csvFileList.size() == 0)
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndianCensusDAO> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
		this.sort(censusComparator);
		return new Gson().toJson(this.csvFileList);
	}
	
	public String getStateCodeSortedIndiaStateCodedata(String csvFilePath) throws CensusAnalyserException {
		loadIndiaStateCodeData(csvFilePath);
		if (stateFileList == null || stateFileList.size() == 0)
			throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		Comparator<IndiaStateCodeCSV> censusComparator = Comparator.comparing(census -> census.stateCode);
		this.sortStateCode(censusComparator);
		return new Gson().toJson(this.stateFileList);
	}

	private <E> void sort(Comparator<IndianCensusDAO> censusComparator) {
		for (int i = 0; i < csvFileList.size(); i++) {
			for (int j = 0; j < csvFileList.size() - i - 1; j++) {
				IndianCensusDAO census1 = csvFileList.get(j);
				IndianCensusDAO census2 = csvFileList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					csvFileList.set(j, census2);
					csvFileList.set(j + 1, census1);
				}
			}
		}
	}

	private void sortStateCode(Comparator<IndiaStateCodeCSV> censusComparator) {
		for (int i = 0; i < stateFileList.size(); i++) {
			for (int j = 0; j < stateFileList.size() - i - 1; j++) {
				IndiaStateCodeCSV census1 = stateFileList.get(j);
				IndiaStateCodeCSV census2 = stateFileList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					stateFileList.set(j, census2);
					stateFileList.set(j + 1, census1);
				}
			}
		}
	}
}