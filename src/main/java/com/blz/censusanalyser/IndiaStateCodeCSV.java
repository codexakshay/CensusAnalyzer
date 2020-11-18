package com.blz.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

	@CsvBindByName(column = "SrNo", required = true)
	public int serialNum;

	@CsvBindByName(column = "State Name", required = true)
	public String state;

	@CsvBindByName(column = "TIN", required = true)
	public int tinNum;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	
	public IndiaStateCodeCSV(IndiaStateCodeCSV indiaStateCode) {
		serialNum = indiaStateCode.serialNum;
		state = indiaStateCode.state;
		tinNum = indiaStateCode.tinNum;
		stateCode = indiaStateCode.stateCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "SrNo='" + serialNum + '\'' + ", State Name='" + state + '\'' + ", Tax Identification Number='"
				+ tinNum + '\'' + ", StateCode='" + stateCode + '\'' + '}';
	}
}
