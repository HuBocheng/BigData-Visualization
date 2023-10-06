package com.game.br.entity;

import java.io.Serializable;

/**
 * @ClassName:WorldwideMedalOverview
 * @description：1984年后全世界各国获奖牌情况
 * @author:BochengHu
 * @date 2023-07-17  16:32
 */
public class WorldwideMedalOverview implements Serializable {
    private String country;
    private int year;
    private int totalGoldNum;
    private int totalSilverNum;
    private int totalBronzeNum;
    private int totalNum;


    public WorldwideMedalOverview() {
    }

    public WorldwideMedalOverview(String country, int year,int totalGoldNum, int totalSilverNum, int totalBronzeNum, int totalNum) {
        this.country = country;
        this.year=year;
        this.totalGoldNum = totalGoldNum;
        this.totalSilverNum = totalSilverNum;
        this.totalBronzeNum = totalBronzeNum;
        this.totalNum = totalNum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "WorldwideMedalOverview{" +
                "country='" + country + '\'' +
                ", totalGoldNum=" + totalGoldNum +
                ", totalSilverNum=" + totalSilverNum +
                ", totalBronzeNum=" + totalBronzeNum +
                ", totalNum=" + totalNum +
                '}';
    }
}
