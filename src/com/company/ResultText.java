package com.company;

public class ResultText
{
    public String cityName;
    public double citytemp;
    public double feeltemp;
    public double humidity;

    public ResultText(String cityName, double citytemp, double feeltemp, double humidity){
        this.cityName = cityName;
        this.citytemp = citytemp;
        this.feeltemp = feeltemp;
        this.humidity = humidity;
    }
    public String toString() {
        return "Город: " + cityName + "\nТемпература: " + citytemp + "\nОщущается как: " + citytemp + "\nВлажность: " + humidity ;
    }
}
