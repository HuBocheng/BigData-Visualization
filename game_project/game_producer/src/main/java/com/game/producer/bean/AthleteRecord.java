package com.game.producer.bean;

import com.game.common.bean.Data;

import java.util.Objects;

/**
 * @ClassName:AthleteRecord
 * @description：原始运动员数据类
 * @author:BochengHu
 * @date 2023-07-11  23:27
 */
public class AthleteRecord extends Data {
    private int ID;
    private String name;
    private String sex;
    private int age;
    private double height;
    private double weight;
    private String NOC;
    private int year;
    private String season;
    private String sport;
    private String event;
    private String medal;


    @Override
    public void setValue(Object val) {
        content=(String)val;
        String[] split = content.split("\t");
        setID(Integer.parseInt(split[0]));
        setName(split[1]);
        setSex(split[2]);
        if(!Objects.equals(split[3], "NA")){
            setAge(Integer.parseInt(split[3]));
        }
        if(!Objects.equals(split[4], "NA")){
            setHeight(Double.parseDouble(split[4]));
        }
        if(!Objects.equals(split[5], "NA")){
            setWeight( Double.parseDouble(split[5]));
        }
        setNOC(split[6]);
        setYear(Integer.parseInt(split[7]));
        setSeason(split[8]);
        setSport(split[9]);
        setEvent(split[10]);
        setMedal(split[11]);
    }

    public AthleteRecord(int ID, String name, String sex, int age, double height, double weight,  String NOC,  int year, String season,  String sport, String event, String medal) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.NOC = NOC;
        this.year = year;
        this.season = season;
        this.sport = sport;
        this.event = event;
        this.medal = medal;
    }

    public AthleteRecord() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNOC() {
        return NOC;
    }

    public void setNOC(String NOC) {
        this.NOC = NOC;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    @Override
    public String toString() {
        return  ID +
                "\t"+ name +
                "\t" + sex +
                "\t" + age +
                "\t" + height +
                "\t" + weight +
                "\t" + NOC  +
                "\t" + year +
                "\t" + season +
                "\t" + sport  +
                "\t'" + event  +
                "\t" + medal;
    }
}
