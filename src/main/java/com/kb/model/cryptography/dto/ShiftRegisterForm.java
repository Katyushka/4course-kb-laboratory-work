package com.kb.model.cryptography.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 25.10.15.
 */
public class ShiftRegisterForm implements Serializable {

    private int n=3;
    private int orderPolynom;
    private int orderSequence;
    private List<Integer> initialVector = new ArrayList<>();
    private List<Integer> resultVector = new ArrayList<>();
    private String result;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Integer> getInitialVector() {
        return initialVector;
    }

    public void setInitialVector(List<Integer> initialVector) {
        this.initialVector = initialVector;
    }

    public List<Integer> getResultVector() {
        return resultVector;
    }

    public void setResultVector(List<Integer> resultVector) {
        this.resultVector = resultVector;
    }

    public int getOrderPolynom() {
        return orderPolynom;
    }

    public void setOrderPolynom(int orderPolynom) {
        this.orderPolynom = orderPolynom;
    }

    public int getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(int orderSequence) {
        this.orderSequence = orderSequence;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
