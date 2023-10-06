package com.game.br.entity;

import java.io.Serializable;

/**
 * @ClassName:AthleteRecord
 * @description：各国田径表现实体类
 * @author:BochengHu
 * @date 2023-07-14  16:28
 */
public class AthleteRecord implements Serializable {
    private String id;
    private String name;
    private String country;
    private String sport;
    private int goldNum;
    private int silverNum;
    private int broonzeNum;
    private int score;

    public AthleteRecord() {
    }

    public AthleteRecord(String id, String name, String country, String sport, int goldNum, int silverNum, int broonzeNum, int score) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.sport = sport;
        this.goldNum = goldNum;
        this.silverNum = silverNum;
        this.broonzeNum = broonzeNum;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }

    public int getSilverNum() {
        return silverNum;
    }

    public void setSilverNum(int silverNum) {
        this.silverNum = silverNum;
    }

    public int getBroonzeNum() {
        return broonzeNum;
    }

    public void setBroonzeNum(int broonzeNum) {
        this.broonzeNum = broonzeNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "AthleteRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", sport='" + sport + '\'' +
                ", goldNum=" + goldNum +
                ", silverNum=" + silverNum +
                ", broonzeNum=" + broonzeNum +
                ", score=" + score +
                '}';
    }
}
