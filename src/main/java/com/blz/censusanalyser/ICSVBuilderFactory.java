package com.blz.censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilderFactory {

	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;

	public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
