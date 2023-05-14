package com.example.restjava.database;

import org.springframework.data.jpa.repository.JpaRepository;

// интерфейс для взаимодействия с таблицами БД
public interface IRepository extends JpaRepository<DbEntity, Integer> {
}




// http://localhost:8080/getresultpair -        get-запрос
// http://localhost:8080/getcash -              кэш
// http://localhost:8080/resetcounters          сброс счетчиков
// http://localhost:8080/getcounters -          получение счетчиков
// http://localhost:8080/getresultpairasync -   асинхронные get запрос
// http://localhost:8080/getbyid  -             получить по id
// http://localhost:8080/getdb -                БД
// http://localhost:8080/getresultpairs -       bulk
/*
[
{
    "a": 1,
    "b": 2,
    "c": 3,
    "d": 4
},
{
    "a": 5,
    "b": 6,
    "c": 7,
    "d": 8
},
{
    "a": 9,
    "b": 10,
    "c": 11,
    "d": 12
},
{
    "a": 13,
    "b": 14,
    "c": 15,
    "d": 16
},
{
    "a": 7,
    "b": 7,
    "c": 7,
    "d": 7
}
]
*/