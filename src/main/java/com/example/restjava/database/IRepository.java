package com.example.restjava.database;

import org.springframework.data.jpa.repository.JpaRepository;

// интерфейс для взаимодействия с таблицами БД
public interface IRepository extends JpaRepository<DbEntity, Integer> {
}
