package com.example.restjava.services;

import com.example.restjava.entity.Counters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterServiceTest {
    private CounterService service = new CounterService();
    @Test
    public void testIncrementing(){
        service.incrementSynchronizedCount();
        service.incrementUnsynchronizedCount();
        Assertions.assertEquals(service.getCounters(), new Counters(1,1));

        service.resetCounters();
        Assertions.assertEquals(service.getCounters(), new Counters(0,0));
    }
}
