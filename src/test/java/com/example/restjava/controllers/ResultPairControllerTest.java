package com.example.restjava.controllers;

import com.example.restjava.database.DbEntity;
import com.example.restjava.database.Repository;
import com.example.restjava.entity.*;
import com.example.restjava.exceptions.ServerException;
import com.example.restjava.memory.InMemoryStorage;
import com.example.restjava.services.AgregateService;
import com.example.restjava.services.CounterService;
import com.example.restjava.services.MathService;
import com.example.restjava.validators.ErrorList;
import com.example.restjava.validators.NumbersValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ResultPairControllerTest {
    @Mock
    private MathService mathService;

    @Mock
    private NumbersValidator validator;

    @Mock
    private CounterService counterService;

    @Mock
    private InMemoryStorage inMemoryStorage;

    @Mock
    private AgregateService agregateService;

    @Mock
    private Repository repository;

    @InjectMocks
    private ResultPairController controller = new ResultPairController(mathService,
            validator, counterService, inMemoryStorage, agregateService, repository);


    // тест с ошибками валидации
    @Test
    public void testGetResultPair1(){
        int[] nums = new int[]{1,1,1,1};
        ErrorList errors = new ErrorList();
        errors.add("All numbers are equals");

        when(validator.Validate(isA(Numbers.class))).thenReturn(errors);
        ResponseEntity<Object> responseEntity = controller.getResultPair(nums);
        errors = (ErrorList)responseEntity.getBody();

        assertNotNull(errors);
        assertEquals(errors.size(), 1);
        assertTrue(errors.getErrors().contains("All numbers are equals"));
    }

    // тест без ошибок валидации
    @Test
    public void testGetResultPair2(){
        int nums[] = new int[]{1,2,3,4};

        when(validator.Validate(isA(Numbers.class))).thenReturn(new ErrorList());
        when(mathService.getResult(isA(Numbers.class))).thenReturn(new ResultPair(2.0, 2.0));

        ResponseEntity<Object> responseEntity = controller.getResultPair(nums);
        ResultPair resultPair = (ResultPair)responseEntity.getBody();

        assertNotNull(resultPair);
        assertEquals(resultPair, new ResultPair(2.0, 2.0));
    }

    // тест с выбросом исключения
    @Test
    public void testGetResultPair3(){
        int nums[] = new int[]{};

        when(validator.Validate(isA(Numbers.class))).thenReturn(new ErrorList());
        when(mathService.getResult(isA(Numbers.class))).thenThrow(ServerException.class);

        ResponseEntity<Object> responseEntity = controller.getResultPair(nums);
        ErrorList errors = (ErrorList)responseEntity.getBody();

        assertNotNull(errors);
        assertEquals(errors.getErrors().size(), 1);
    }

    @Test
    public void testGetResultPairs(){
        List<BulkParameter> parameters = new ArrayList<>();
        parameters.add(new BulkParameter(1,2,3,4));
        parameters.add(new BulkParameter(5,6,7,8));
        parameters.add(new BulkParameter(9,10,11,12));

        when(validator.Validate(isA(Numbers.class))).thenReturn(new ErrorList());
        when(mathService.getResult(isA(Numbers.class))).thenReturn(new ResultPair(1.0, 1.0));

        doNothing().when(repository).save(isA(Numbers.class), isA(ResultPair.class));
        doNothing().when(inMemoryStorage).add(isA(Numbers.class), isA(ResultPair.class));

        ResponseEntity<Object> responseEntity = controller.getResultPairs(parameters);
        BulkResultObject resultObject = (BulkResultObject)responseEntity.getBody();

        assertNotNull(resultObject);
        assertTrue(resultObject.getErrors().isEmpty());
        assertFalse(resultObject.getResults().isEmpty());
    }

    @Test
    public void testGetCash(){
        Map<Numbers, ResultPair> cash = new HashMap<>();
        cash.put(new Numbers(new int[]{1,2,3,4}), new ResultPair(2.0, 2.0));
        cash.put(new Numbers(new int[]{5,6,7,8}), new ResultPair(6.0, 6.0));

        when(inMemoryStorage.getMap()).thenReturn(cash);
        Map<Numbers, ResultPair> map = controller.getCash().getBody();

        assertNotNull(map);
        assertEquals(map.size(), 2);
    }

    @Test
    public void testGetDb(){
        List<DbEntity> entities = new ArrayList<>();
        entities.add(new DbEntity(new Numbers(new int[]{1,2,3,4}), new ResultPair(2.0, 2.0)));
        entities.add(new DbEntity(new Numbers(new int[]{5,6,7,8}), new ResultPair(6.0, 6.0)));

        when(repository.getAll()).thenReturn(entities);
        List<DbEntity> list = controller.getDb().getBody();

        assertNotNull(list);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testCounters(){
        when(counterService.getCounters()).thenReturn(new Counters(0,0));
        ResponseEntity<Counters> responseEntity = controller.resetCounters();
        Counters counters = responseEntity.getBody();
        assertEquals(counters, new Counters(0, 0));
    }
}
