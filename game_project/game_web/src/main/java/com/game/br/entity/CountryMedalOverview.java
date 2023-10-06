package com.game.br.entity;

import java.io.Serializable;

/**
 * @ClassName:CountryMedalOverview
 * @description：Todo
 * @author:BochengHu
 * @date 2023-07-16  11:26
 */
public class CountryMedalOverview implements Serializable {
    private int year;
    private int totalGoldNum;
    private int totalSilverNum;
    private int totalBronzeNum;
    private int totalNum;

    public CountryMedalOverview() {
    }

    public CountryMedalOverview( int year, int totalGoldNum, int totalSilverNum, int totalBronzeNum, int totalNum) {
        this.year = year;
        this.totalGoldNum = totalGoldNum;
        this.totalSilverNum = totalSilverNum;
        this.totalBronzeNum = totalBronzeNum;
        this.totalNum = totalNum;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalGoldNum() {
        return totalGoldNum;
    }

    public void setTotalGoldNum(int totalGoldNum) {
        this.totalGoldNum = totalGoldNum;
    }

    public int getTotalSilverNum() {
        return totalSilverNum;
    }

    public void setTotalSilverNum(int totalSilverNum) {
        this.totalSilverNum = totalSilverNum;
    }

    public int getTotalBronzeNum() {
        return totalBronzeNum;
    }

    public void setTotalBronzeNum(int totalBronzeNum) {
        this.totalBronzeNum = totalBronzeNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "CountryMedalOverview{" +
                ", year=" + year +
                ", totalGoldNum=" + totalGoldNum +
                ", totalSilverNum=" + totalSilverNum +
                ", totalBronzeNum=" + totalBronzeNum +
                ", totalNum=" + totalNum +
                '}';
    }
}
