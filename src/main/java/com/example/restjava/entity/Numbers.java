package com.example.restjava.entity;

import java.util.Arrays;

public class Numbers {
    private int[] numbers;           //значения

    public Numbers(){}
    public Numbers(int[] nums){
        numbers = nums;
    }

    public int[] getNumbers(){
        return numbers;
    }

    @Override
    public String toString(){
        String result = "";
        for(int number:numbers){
            result = result + number + " ";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Numbers numbers1 = (Numbers) o;
        return Arrays.equals(numbers, numbers1.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }
}
