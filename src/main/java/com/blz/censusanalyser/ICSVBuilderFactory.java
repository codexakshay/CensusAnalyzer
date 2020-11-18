package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilderFactory {

	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException;
}
