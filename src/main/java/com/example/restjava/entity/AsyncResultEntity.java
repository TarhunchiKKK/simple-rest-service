package com.example.restjava.entity;

// класс-результат асинхронного get-метода
public class AsyncResultEntity {
    private String message;             // сообщение
    private long id;                    // id добавленного элемента
    public AsyncResultEntity(long id){  // конструктор
        message = "Your element can be getted from db by index:";
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
