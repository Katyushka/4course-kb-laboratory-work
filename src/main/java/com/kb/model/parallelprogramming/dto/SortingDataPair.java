package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;

public class SortingDataPair implements Serializable {
    private static final long serialVersionUID = 826206437453398637L;

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
