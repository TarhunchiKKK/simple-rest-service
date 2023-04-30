package com.example.restjava.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorList {
    private List<String> errors;                            //список ошибок
    private String status;                                  //статус
    public ErrorList(){
        errors = new ArrayList<String>();
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public List<String> getErrors(){
        return errors;
    }
    public void add(String error){
        errors.add(error);
    }   //добавление сообщения об ошибке
    public void add(Exception error){
        errors.add(error.getMessage());
    }//добавление сообщения об ошибке
    public void add(ErrorList errors){
        errors.getErrors().forEach(error -> this.errors.add(error));
    }

    public int size(){                                      //кол-во сообщений об ошибке
        return errors.size();
    }
    public boolean isEmpty(){                               //нет ли ошибок
        return errors.size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorList errors1 = (ErrorList) o;
        return Objects.equals(errors, errors1.errors) && Objects.equals(status, errors1.status);
    }
    @Override
    public int hashCode() {
        return Objects.hash(errors, status);
    }
}
