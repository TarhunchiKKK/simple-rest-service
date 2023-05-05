package com.example.restjava.entity;

// класс с агрегирующими значениями
public class AgregateValues {
    private double minMediana;                                  // агрегирующие значения
    private double maxMediana;
    private double middleMediana;
    private double minMidddleValue;
    private double maxMiddleValue;
    private double middleMiddleValue;

    public AgregateValues(){}

    public double getMinMediana() {
        return minMediana;
    }       // геттеры

    public double getMaxMediana() {
        return maxMediana;
    }

    public double getMiddleMediana() {
        return middleMediana;
    }

    public double getMinMidddleValue() {
        return minMidddleValue;
    }

    public double getMaxMiddleValue() {
        return maxMiddleValue;
    }

    public double getMiddleMiddleValue() {
        return middleMiddleValue;
    }

    public void setMinMediana(double minMediana) {
        this.minMediana = minMediana;
    }   // сеттеры

    public void setMaxMediana(double maxMediana) {
        this.maxMediana = maxMediana;
    }

    public void setMiddleMediana(double middleMediana) {
        this.middleMediana = middleMediana;
    }

    public void setMinMidddleValue(double minMidddleValue) {
        this.minMidddleValue = minMidddleValue;
    }

    public void setMaxMiddleValue(double maxMiddleValue) {
        this.maxMiddleValue = maxMiddleValue;
    }

    public void setMiddleMiddleValue(double middleMiddleValue) {
        this.middleMiddleValue = middleMiddleValue;
    }
}
