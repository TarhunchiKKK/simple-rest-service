package com.example.restjava.services;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import com.example.restjava.exceptions.ServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathServiceTest {
    private MathService service = new MathService();
    @Test
    public void testGood(){
        Numbers numbers = new Numbers(new int[]{3,2,4,1});
        ResultPair good = new ResultPair(2.0,2.0);
        ResultPair result = service.getResult(numbers);
        Assertions.assertEquals(good, result);
    }

    @Test
    public void testEmpty(){
        Numbers numbers = new Numbers(new int[]{});
        ResultPair result = new ResultPair();
        Throwable throwable = assertThrows(ServerException.class,
                () -> service.getResult(numbers));
        assertNotNull(throwable.getMessage());
    }
}
