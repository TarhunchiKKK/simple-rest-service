package com.example.restjava.services;

import com.example.restjava.entity.AgregateValues;
import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AgregateService {
    // компаратор для агрегирующего функционала со средним значением
    private static Comparator<ResultPair> middleValueComparator
            = new Comparator<ResultPair>() {
        @Override
        public int compare(ResultPair r1, ResultPair r2) {
            return Double.compare(r1.getMiddleValue(), r2.getMiddleValue());
        }
    };

    // компаратор для агрегирующего функционала с медианой
    private static Comparator<ResultPair> medianaComparator
            = new Comparator<ResultPair>() {
        @Override
        public int compare(ResultPair r1, ResultPair r2) {
            return Double.compare(r1.getMediana(), r2.getMediana());
        }
    };

    // асинхронное получение агрегирующих значений
    public void getAgregateValues(Collection<ResultPair> collection, AgregateValues agregateValues){
        CompletableFuture.runAsync(()->agregateValues.setMinMediana((getMinMediana(collection))));
        CompletableFuture.runAsync(()->agregateValues.setMaxMediana(getMaxMediana(collection)));
        CompletableFuture.runAsync(()->agregateValues.setMiddleMediana(getMiddleMediana(collection)));
        CompletableFuture.runAsync(()->agregateValues.setMinMidddleValue(getMinMiddleValue(collection)));
        CompletableFuture.runAsync(()->agregateValues.setMaxMiddleValue(getMaxMiddleValue(collection)));
        CompletableFuture.runAsync(()->agregateValues.setMiddleMiddleValue(getMiddleMiddleValue(collection)));
    }

    // минимальное среднее значение
    public double getMinMiddleValue(Collection<ResultPair> collection){
        return collection.stream().min(middleValueComparator).get().getMiddleValue();
    }

    // максимальное среднее значение
    public double getMaxMiddleValue(Collection<ResultPair> collection){
        return collection.stream().max(middleValueComparator).get().getMiddleValue();
    }

    // среднее среднее значение
    public double getMiddleMiddleValue(Collection<ResultPair> collection){
        int sum = 0;
        for(ResultPair value:collection){
            sum += value.getMiddleValue();
        }
        return sum / collection.size();
    }


    // минимальная медиана
    public double getMinMediana(Collection<ResultPair> collection){
        return collection.stream().min(medianaComparator).get().getMediana();
    }

    // максиммальная медиана
    public double getMaxMediana(Collection<ResultPair> collection){
        return collection.stream().max(medianaComparator).get().getMediana();
    }

    // среднее значение медианы
    public double getMiddleMediana(Collection<ResultPair> collection){
        int sum = 0;
        for(ResultPair value:collection){
            sum += value.getMediana();
        }
        return sum / collection.size();
    }
}
