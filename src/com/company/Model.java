package com.company;

public class Model
{
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    private String name;
    private double temp;
    private double humidity;
    private String icon;
    private String main;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }




}
