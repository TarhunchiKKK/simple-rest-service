package com.example.restjava.services;

import com.example.restjava.entity.Counters;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private Integer synchronizedCount = Integer.valueOf(0);
    private Integer unsynchronizedCount = Integer.valueOf(0);
    public synchronized void incrementSynchronizedCount(){
        synchronizedCount++;
    }

    public void incrementUnsynchronizedCount(){
        unsynchronizedCount++;
    }

    public void resetCounters(){
        synchronizedCount = unsynchronizedCount = Integer.valueOf(0);
    }

    public Counters getCounters(){
        return new Counters(synchronizedCount, unsynchronizedCount);
    }
}

