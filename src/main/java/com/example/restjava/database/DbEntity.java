package com.example.restjava.database;

import com.example.restjava.entity.Numbers;
import com.example.restjava.entity.ResultPair;
import jakarta.persistence.*;

import java.util.Objects;


// сущность БД
@Entity
@Table(name = "results")
public class DbEntity {
    @Id                             // поле является ключем
    @Column(name = "id")            // имя столбца талицы для поля
    // стратегия генерации ключа
//    @SequenceGenerator(name = "resultsIdSeq", sequenceName = "results_id_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultsIdSeq")
    private Integer id;             // идентификатор

    @Column(name = "numbers")
    private String numbers;         // введенные числа

    @Column(name = "middle_value")
    private double middleValue;     // среднее значение

    @Column(name = "mediana")
    private double mediana;         // медианное значение

    public DbEntity(){              // конструктор по умолчанию(необходим для добавления в БД)

    }

    public DbEntity(Numbers numbers, ResultPair resultPair){
        this.numbers = "";
        for(int number:numbers.getNumbers()){   // преобразование массива чисел в строку
            this.numbers = this.numbers + number + " ";
        }
        middleValue = resultPair.getMiddleValue();;
        mediana = resultPair.getMediana();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public double getMiddleValue() {
        return middleValue;
    }

    public void setMiddleValue(double middleValue) {
        this.middleValue = middleValue;
    }

    public double getMediana() {
        return mediana;
    }

    public void setMediana(double mediana) {
        this.mediana = mediana;
    }

}
