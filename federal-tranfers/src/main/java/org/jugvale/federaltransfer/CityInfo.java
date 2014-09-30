/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.federaltransfer;

/**
 *
 * @author william
 */
public class CityInfo {

    private String cityName;
    private long hdi;
    private long hdiIncome;
    private long hdiEducation;
    private long hdiLifeExpectancy;
    private long population;

    public CityInfo() {
    }

    public CityInfo(String cityName, long hdi, long hdiIncome, long hdiEducation, long hdiLifeExpectancy, long population) {
        this.cityName = cityName;
        this.hdi = hdi;
        this.hdiIncome = hdiIncome;
        this.hdiEducation = hdiEducation;
        this.hdiLifeExpectancy = hdiLifeExpectancy;
        this.population = population;
    }

}
