package com.example.restjava.entity;

public class ResultPair {
    private double middleValue;         //среднее значение
    private double mediana;             //медианное значение
    public ResultPair(){
            middleValue = mediana = 0.0;
        }
    public ResultPair(double middleValue, double mediana) {
        this.middleValue = middleValue;
        this.mediana = mediana;
    }
    public double getMiddleValue() {
            return middleValue;
        }
    public double getMediana() {
            return mediana;
        }
    public void setMiddleValue(double middleValue) {
            this.middleValue = middleValue;
        }
    public void setMediana(double mediana) {
            this.mediana = mediana;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultPair that = (ResultPair) o;
        return that.middleValue == middleValue && that.mediana == mediana;
    }
}
