package com.example.testingtask.data.serviceBRequest;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Orlov Diga
 */
public class MessageB implements Serializable {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String txt;
    private String createdDt;
    private Integer currentTemp;

    public MessageB() {
        this.createdDt = dateFormat.format(new Date());
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public Integer getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Integer currentTemp) {
        this.currentTemp = currentTemp;
    }

    @Override
    public String toString() {
        return "MessageB{" +
                "txt='" + txt + '\'' +
                ", createdDt='" + createdDt + '\'' +
                ", currentTemp=" + currentTemp +
                '}';
    }
}
