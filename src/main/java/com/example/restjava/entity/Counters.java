package com.example.restjava.entity;

import java.util.Objects;

// класс со счетчиками
public class Counters {
    private int synchronizedCounter;
    private int unsynchronizedCounter;
    public Counters(int s, int u){
        synchronizedCounter = s;
        unsynchronizedCounter = u;
    }

    // сброс счетчиков
    public void resetCounters(){
        synchronizedCounter = unsynchronizedCounter = 0;
    }

    public int getSynchronizedCounter() {
        return synchronizedCounter;
    }

    public int getUnsynchronizedCounter() {
        return unsynchronizedCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counters counters = (Counters) o;
        return synchronizedCounter == counters.synchronizedCounter && unsynchronizedCounter == counters.unsynchronizedCounter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(synchronizedCounter, unsynchronizedCounter);
    }
}
