package com.example.restjava.entity;

import java.util.Objects;

// объект для bulk запроса
public class BulkParameter {
    private int a;      // числа
    private int b;
    private int c;
    private int d;
    public BulkParameter(){}
    public BulkParameter(int a, int b, int c, int d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
    public Numbers toNumbers(){
        return new Numbers(new int[]{a, b, c, d});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulkParameter that = (BulkParameter) o;
        return a == that.a && b == that.b && c == that.c && d == that.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}
