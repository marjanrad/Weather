package com.example.marjanraad.weather;

public class ModelsList {
    String date;
    String day;
    String low;
    String high;
    String text;

    public ModelsList(String date, String day, String low, String hight, String text) {
        this.date = date;
        this.day = day;
        this.low = low;
        this.high = hight;
        this.text = text;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
