package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05.10.15.
 */
public class SortingForm implements Serializable {
    private List<Integer> firstData = new ArrayList<>();
    private List<Integer> secondData = new ArrayList<>();

    public List<Integer> getSecondData() {
        return secondData;
    }

    public void setSecondData(List<Integer> secondData) {
        this.secondData = secondData;
    }

    public List<Integer> getFirstData() {
        return firstData;
    }

    public void setFirstData(List<Integer> firstData) {
        this.firstData = firstData;
    }
}
