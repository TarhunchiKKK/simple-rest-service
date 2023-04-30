package com.example.restjava.services;

import com.example.restjava.entity.AgregateValues;
import com.example.restjava.entity.ResultPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class AgregateServiceTest {
    private AgregateService service = new AgregateService();

    @Test
    public void testGetAgregateValues(){
        AgregateValues values = new AgregateValues();
        Collection<ResultPair> resultPairs = new ArrayList<>();
        resultPairs.add(new ResultPair(1.0, 5.0));
        resultPairs.add(new ResultPair(3.0, 3.0));
        resultPairs.add(new ResultPair(5.0, 1.0));

        service.getAgregateValues(resultPairs, values);
        Assertions.assertEquals(values.getMinMediana(), 1.0);
        Assertions.assertEquals(values.getMaxMediana(), 5.0);
        Assertions.assertEquals(values.getMiddleMediana(), 3.0);
        Assertions.assertEquals(values.getMinMidddleValue(), 1.0);
        Assertions.assertEquals(values.getMaxMiddleValue(), 5.0);
        Assertions.assertEquals(values.getMiddleMiddleValue(), 3.0);
    }
}
