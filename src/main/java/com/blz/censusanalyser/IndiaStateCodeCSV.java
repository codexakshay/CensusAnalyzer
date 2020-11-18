package com.blz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

	@CsvBindByName(column = "SrNo", required = true)
	public String serialNum;

	@CsvBindByName(column = "State Name", required = true)
	public int state;

	@CsvBindByName(column = "TIN", required = true)
	public int tinNum;

	@CsvBindByName(column = "StateCode", required = true)
	public int stateCode;

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "SrNo='" + serialNum + '\'' + ", State Name='" + state + '\'' + ", Tax Identification Number='"
				+ tinNum + '\'' + ", StateCode='" + stateCode + '\'' + '}';
	}
}
