package com.example.restjava.memory;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class InMemoryStorage {
    //хранилище (ключ - входные числа(Numbers), значение - полученный результат(ResultPair))
    private Map<Numbers, ResultPair> dataStorage = new HashMap<Numbers, ResultPair>();

    private int size;

    //добавление
    public void add(Numbers numbers, ResultPair resultPair){
        dataStorage.put(numbers, resultPair);
        size = dataStorage.size();;
    }

    public int size() {return size;}

    public Map<Numbers, ResultPair> getMap(){return dataStorage;}

    //получение результата по его медиане
    public ResultPair getByMediana(double mediana){
        for(ResultPair resultPair: dataStorage.values()){
            if(mediana == resultPair.getMediana()) return resultPair;
        }
        return null;
    }

    //получение результата по его среднему значению
    public ResultPair getByMiddleValue(double middleValue){
        for(ResultPair resultPair: dataStorage.values()){
            if(middleValue == resultPair.getMediana()) return resultPair;
        }
        return null;
    }
}
