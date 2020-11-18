package com.blz.censusanalyser;

public class CSVBuilderFactory {

	public static ICSVBuilderFactory createCsvBuilder() {
		return new OpenCsvBuilder();
	}

}
