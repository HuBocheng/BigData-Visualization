package com.game.mr.entity;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName:FinanceData
 * @description：金融数据实体类
 * @author:BochengHu
 * @date 2023-07-16  16:40
 */
public class FinanceData implements DBWritable, Writable {
    private int year;
    private String  country;
    private float totalGDP ;
    private float averageGDP;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(country);
        dataOutput.writeInt(year);
        dataOutput.writeFloat(totalGDP);
        dataOutput.writeFloat(averageGDP);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        country=dataInput.readUTF();
        year=dataInput.readInt();
        totalGDP=dataInput.readFloat();
        averageGDP=dataInput.readFloat();
    }
    @Override
    public void write(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,this.country);
        preparedStatement.setInt(2,this.year);
        preparedStatement.setFloat(3,this.totalGDP);
        preparedStatement.setFloat(4,this.averageGDP);
    }

    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.country=resultSet.getString("country");
        this.year=resultSet.getInt("year");
        this.totalGDP=resultSet.getFloat("totalGDP");
        this.averageGDP=resultSet.getFloat("averageGDP");

    }



    public FinanceData() {
        totalGDP= (float) -1.0;
        averageGDP= (float) -1.0;
    }

    public FinanceData(int year, String country, float totalGDP, float averageGDP) {
        this.country = country;
        this.year = year;
        this.totalGDP = totalGDP;
        this.averageGDP = averageGDP;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "FinanceData{" +
                "year=" + year +
                ", country='" + country + '\'' +
                ", totalGDP=" + totalGDP +
                ", averageGDP=" + averageGDP +
                '}';
    }
}
