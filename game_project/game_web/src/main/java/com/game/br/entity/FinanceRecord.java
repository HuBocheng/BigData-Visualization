package com.game.br.entity;

import java.io.Serializable;

/**
 * @ClassName:FinanceRecord
 * @description：金融数据的实体类
 * @author:BochengHu
 * @date 2023-07-17  21:52
 */
public class FinanceRecord implements Serializable {
    private int year;
    private String  country;
    private float totalGDP ;
    private float averageGDP;

    public FinanceRecord(int year, String country, float totalGDP, float averageGDP) {
        this.year = year;
        this.country = country;
        this.totalGDP = totalGDP;
        this.averageGDP = averageGDP;
    }

    public FinanceRecord() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getTotalGDP() {
        return totalGDP;
    }

    public void setTotalGDP(float totalGDP) {
        this.totalGDP = totalGDP;
    }

    public float getAverageGDP() {
        return averageGDP;
    }

    public void setAverageGDP(float averageGDP) {
        this.averageGDP = averageGDP;
    }

    @Override
    public String toString() {
        return "FinanceRecord{" +
                "year=" + year +
                ", country='" + country + '\'' +
                ", totalGDP=" + totalGDP +
                ", averageGDP=" + averageGDP +
                '}';
    }
}
