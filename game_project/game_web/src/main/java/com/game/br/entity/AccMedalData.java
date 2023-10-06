package com.game.br.entity;

import java.io.Serializable;

/**
 * @ClassName:AccMedalData
 * @description：截止到XX年份各国获得金银铜牌数目综合情况
 * @author:BochengHu
 * @date 2023-07-18  10:22
 */
public class AccMedalData implements Serializable {
    private String country;
    private int year;
    private int accGoldNum;
    private int accSilverNum;
    private int accBronzeNum;
    private int acctotalNum;

    public AccMedalData() {
    }

    public AccMedalData(String country, int year, int accGoldNum, int accSilverNum, int accBronzeNum, int acctotalNum) {
        this.country = country;
        this.year = year;
        this.accGoldNum = accGoldNum;
        this.accSilverNum = accSilverNum;
        this.accBronzeNum = accBronzeNum;
        this.acctotalNum = acctotalNum;
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

    public int getaccGoldNum() {
        return accGoldNum;
    }

    public void setaccGoldNum(int accGoldNum) {
        this.accGoldNum = accGoldNum;
    }

    public int getaccSilverNum() {
        return accSilverNum;
    }

    public void setaccSilverNum(int accSilverNum) {
        this.accSilverNum = accSilverNum;
    }

    public int getaccBronzeNum() {
        return accBronzeNum;
    }

    public void setaccBronzeNum(int accBronzeNum) {
        this.accBronzeNum = accBronzeNum;
    }

    public int getacctotalNum() {
        return acctotalNum;
    }

    public void setacctotalNum(int acctotalNum) {
        this.acctotalNum = acctotalNum;
    }

    @Override
    public String toString() {
        return "AccMedalData{" +
                "country='" + country + '\'' +
                ", year=" + year +
                ", accGoldNum=" + accGoldNum +
                ", accSilverNum=" + accSilverNum +
                ", accBronzeNum=" + accBronzeNum +
                ", acctotalNum=" + acctotalNum +
                '}';
    }
}
