package com.example.restjava.database;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// сервис для работы с БД
@Service
public class Repository {
    private IRepository repository;

    @Autowired
    public Repository(IRepository repository){
        this.repository = repository;
    }

    // добавление объекта в БД
    public void save(Numbers numbers, ResultPair resultPair){
        if(!contains(numbers)){
            repository.save(new DbEntity(numbers, resultPair));
        }
    }

    // получение всех объектов из БД
    public List<DbEntity> getAll(){
        return repository.findAll();
    }

    public ResultPair get(Numbers numbers){
        for(DbEntity entity:getAll()){
            if(entity.getNumbers().equals(numbers.toString()))
            return new ResultPair(entity.getMediana(), entity.getMiddleValue());
        }
        return null;
    }

    public boolean contains(Numbers numbers){
        DbEntity entity = new DbEntity(numbers, new ResultPair());
        return getAll().stream().anyMatch(e -> e.equals(entity));
    }
}