package com.example.restjava.entity;

import com.example.restjava.validators.ErrorList;

import java.util.*;

public class BulkResultObject {
    private Collection<ResultPair> results = new ArrayList<>();
    private ErrorList errors;
    private AgregateValues agregateValues;
    public BulkResultObject(Collection<ResultPair> results, ErrorList errors, AgregateValues agregateValues){
        this.results = results;
        this.errors = errors;
        this.agregateValues = agregateValues;
    }

    public Collection<ResultPair> getResults() {
        return results;
    }

    public ErrorList getErrors() {
        return errors;
    }

    public AgregateValues getAgregateValues() {
        return agregateValues;
    }
}
