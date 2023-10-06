package org.doraemon.visualize.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("financeshow")
public class GDP {
    private String country;
    private int year;
    @TableField("totalGDP")
    private double totalGDP;
    @TableField("averageGDP")
    private double averageGDP;

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

    public double getTotalGDP() {
        return totalGDP;
    }

    public void setTotalGDP(double totalGDP) {
        this.totalGDP = totalGDP;
    }

    public double getAverageGDP() {
        return averageGDP;
    }

    public void setAverageGDP(double averageGDP) {
        this.averageGDP = averageGDP;
    }

    public GDP(String country, int year, double totalGDP, double averageGDP) {
        this.country = country;
        this.year = year;
        this.totalGDP = totalGDP;
        this.averageGDP = averageGDP;
    }
}
