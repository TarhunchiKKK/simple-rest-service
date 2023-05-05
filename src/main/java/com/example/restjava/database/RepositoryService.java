package com.example.restjava.database;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// сервис для работы с БД
@Service
public class RepositoryService {
    private IRepository repository;

    @Autowired
    public RepositoryService(IRepository repository){
        this.repository = repository;
    }

    // добавление объекта в БД
    public void save(Numbers numbers, ResultPair resultPair){
        repository.save(new DbEntity(numbers, resultPair));
    }

    // получение всех объектов из БД
    public List<DbEntity> getAll(){
        return repository.findAll();
    }

    // получение результата из БД по входным данным
    public ResultPair get(Numbers numbers){
        for(DbEntity entity:getAll()){
            if(entity.getNumbers().equals(numbers.toString()))
            return new ResultPair(entity.getMediana(), entity.getMiddleValue());
        }
        return null;
    }

    // существует ли объект с такими входными данными в БД
    public boolean contains(Numbers numbers){
        DbEntity entity = new DbEntity(numbers, new ResultPair());
        return getAll().stream().anyMatch(e -> e.equals(entity));
    }
}