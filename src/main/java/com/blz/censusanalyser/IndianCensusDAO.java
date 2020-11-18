package com.blz.censusanalyser;

public class IndianCensusDAO {

	public String state;
	public int densityPerSqKm;
    public int areaInSqKm;
    public int population;

    public IndianCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        state = indiaCensusCSV.state;
    }
}
