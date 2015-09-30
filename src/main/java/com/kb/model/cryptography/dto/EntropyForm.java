package com.kb.model.cryptography.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16.09.15.
 */
public class EntropyForm implements Serializable {

    private int m = 4;
    private int k = 3;
    private int countC = 4;
    private List<List<String>> c = new ArrayList<>();
    private List<Double> pM = new ArrayList<>();
    private List<Double> pK = new ArrayList<>();
    private List<Double> pC = new ArrayList<>();

    private double entropyM = 0;
    private double entropyK = 0;
    private double entropyC = 0;
    private double entropyKC = 0;



    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }


    public List<List<String>> getC() {
        return c;
    }

    public void setC(List<List<String>> c) {
        this.c = c;
    }

    public List<Double> getpM() {
        return pM;
    }

    public void setpM(List<Double> pM) {
        this.pM = pM;
    }

    public List<Double> getpK() {
        return pK;
    }

    public void setpK(List<Double> pK) {
        this.pK = pK;
    }

    public List<Double> getpC() {
        return pC;
    }

    public void setpC(List<Double> pC) {
        this.pC = pC;
    }

    public int getCountC() {
        return countC;
    }

    public void setCountC(int countC) {
        this.countC = countC;
    }

    public double getEntropyM() {
        return entropyM;
    }

    public void setEntropyM(double entropyM) {
        this.entropyM = entropyM;
    }

    public double getEntropyC() {
        return entropyC;
    }

    public void setEntropyC(double entropyC) {
        this.entropyC = entropyC;
    }

    public double getEntropyK() {
        return entropyK;
    }

    public void setEntropyK(double entropyK) {
        this.entropyK = entropyK;
    }

    public double getEntropyKC() {
        return entropyKC;
    }

    public void setEntropyKC(double entropyKC) {
        this.entropyKC = entropyKC;
    }
}
