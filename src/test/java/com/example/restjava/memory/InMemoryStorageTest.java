package com.example.restjava.memory;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InMemoryStorageTest {
    private InMemoryStorage inMemoryStorage = new InMemoryStorage();
    @Test
    public void testAdding(){
        inMemoryStorage.add(new Numbers(new int[]{1,2,3,4}), new ResultPair(2.0, 2.0));
        inMemoryStorage.add(new Numbers(new int[]{5,6,7,8}), new ResultPair(6.0, 6.0));
        Assertions.assertEquals(inMemoryStorage.size(), 2);
        Assertions.assertEquals(inMemoryStorage.getByMediana(2.0), new ResultPair(2.0,2.0));
        Assertions.assertNull(inMemoryStorage.getByMediana(3.0));
        Assertions.assertEquals(inMemoryStorage.getByMiddleValue(6.0), new ResultPair(6.0,6.0));
        Assertions.assertNull(inMemoryStorage.getByMiddleValue(3.0));
        Assertions.assertFalse(inMemoryStorage.getMap().isEmpty());
    }
}
