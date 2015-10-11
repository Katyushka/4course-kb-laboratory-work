package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05.10.15.
 */
public class SortingForm implements Serializable {
    private static final long serialVersionUID = -4368991793569531187L;

    private SortingData quickSortingData = new SortingData();
    private SortingData insertionSortingData = new SortingData();

    public SortingData getQuickSortingData() {
        return quickSortingData;
    }

    public void setQuickSortingData(SortingData quickSortingData) {
        this.quickSortingData = quickSortingData;
    }

    public SortingData getInsertionSortingData() {
        return insertionSortingData;
    }

    public void setInsertionSortingData(SortingData insertionSortingData) {
        this.insertionSortingData = insertionSortingData;
    }
}
