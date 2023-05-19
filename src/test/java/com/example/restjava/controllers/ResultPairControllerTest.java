package com.example.restjava.controllers;

import com.example.restjava.database.DbEntity;
import com.example.restjava.database.RepositoryService;
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
    private RepositoryService repositoryService;

    @InjectMocks
    private ResultPairController controller = new ResultPairController(mathService,
            validator, counterService, inMemoryStorage, agregateService, repositoryService);


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

        when(repositoryService.contains(isA(Numbers.class))).thenReturn(false);
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
        when(repositoryService.contains(isA(Numbers.class))).thenReturn(false);

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

        doNothing().when(repositoryService).save(isA(Numbers.class), isA(ResultPair.class));
        doNothing().when(inMemoryStorage).add(isA(Numbers.class), isA(ResultPair.class));

        ResponseEntity<BulkResultObject> responseEntity = controller.getResultPairs(parameters);
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

        when(repositoryService.getAll()).thenReturn(entities);
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



    /*--------------------------------------------------------------------------------------------------------------*/
    @Test
    public void testAsyncWithValidationErrors(){
        int nums[] = new int[]{ 1, 1, 1, 1 };
        ErrorList errors = new ErrorList();
        errors.add("All numbers are equals");

        when(validator.Validate(any(Numbers.class))).thenReturn(errors);

        ErrorList errorList = (ErrorList) controller.getResultPairAsync(nums).getBody();
        assertNotNull(errorList);
        assertTrue(errorList.size() == errors.size());
        assertEquals(errorList.getErrors().get(0), errors.getErrors().get(0));
    }

    @Test
    public void testAsyncGood(){
        int nums[] = new int[]{ 1, 2, 3, 4 };

        when(validator.Validate(any(Numbers.class))).thenReturn(new ErrorList());
        doNothing().when(counterService).incrementUnsynchronizedCount();
        doNothing().when(counterService).incrementSynchronizedCount();
        when(repositoryService.contains(any(Numbers.class))).thenReturn(false);
        when(repositoryService.size()).thenReturn(Long.valueOf(6));
        when(mathService.getResult(any(Numbers.class))).thenReturn(null);
        doNothing().when(repositoryService).save(any(Numbers.class), any(ResultPair.class), any(Long.class));
        doNothing().when(inMemoryStorage).add(any(Numbers.class), any(ResultPair.class));

        AsyncResultEntity result = (AsyncResultEntity) controller.getResultPairAsync(nums).getBody();
        assertNotNull(result);
        assertTrue(result.getId() == 7);
        assertEquals(result.getMessage(), "Your element can be getted from db by index:");
    }

    @Test
    public void testBulkAsync(){
        int id = 1;
        BulkParameter b1 = new BulkParameter(1,2,3,4);
        BulkParameter b2 = new BulkParameter(5,6,7,8);
        BulkParameter b3 = new BulkParameter(9,10,11,12);
        BulkParameter b4 = new BulkParameter(13,14,15,16);
        List<BulkParameter> parameters = new ArrayList<>();
        parameters.add(b1);
        parameters.add(b2);
        parameters.add(b3);
        parameters.add(b4);

        when(repositoryService.getNextId()).thenReturn(
                Long.valueOf(1), Long.valueOf(2),Long.valueOf(3),Long.valueOf(4));
        when(mathService.getResult(any(Numbers.class))).thenReturn(null);
        //doNothing().when(repositoryService).save(any(Numbers.class), any(ResultPair.class), any(Long.class));

        List<Long> list = controller.getResultPairsAsync(parameters).getBody();
        assertNotNull(list);
        assertEquals(list.get(0), 1);
        assertEquals(list.get(1), 2);
        assertEquals(list.get(2), 3);
        assertEquals(list.get(3), 4);
    }

    @Test
    public void testGetById(){
        int id = 5;
        Numbers numbers = new Numbers(new int[]{1,2,3,4});
        ResultPair resultPair = new ResultPair(2.0, 2.0);

        when(repositoryService.getById(any(Long.class))).thenReturn(new DbEntity(numbers, resultPair));

        DbEntity entity = (DbEntity)controller.getById(id).getBody();
        assertNotNull(entity);
        assertEquals(entity.getNumbers(), numbers.toString());
        assertEquals(entity.getMediana(), resultPair.getMediana());
        assertEquals(entity.getMiddleValue(), resultPair.getMiddleValue());
    }
}
