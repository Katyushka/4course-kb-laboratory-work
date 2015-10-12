package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortingForm implements Serializable {
    private static final long serialVersionUID = -4368991793569531187L;

    private List<Integer> quickSortingDataBegin = new ArrayList<>();
    private List<Integer> insertionSortingDataBegin = new ArrayList<>();
    private long quickSortDuration = 0;
    private long insertionSortDuration = 0;
    private String status;

    public SortingForm() {
    }

    public SortingForm(SortingDataPair sortingDataPair) {
        quickSortingDataBegin = new ArrayList<>(sortingDataPair.getQuickSortingData().getData().subList(0, 1000));
        insertionSortingDataBegin = new ArrayList<>(sortingDataPair.getInsertionSortingData().getData().subList(0, 1000));
        quickSortDuration = sortingDataPair.getQuickSortingData().getDuration();
        insertionSortDuration = sortingDataPair.getInsertionSortingData().getDuration();
    }

    public List<Integer> getInsertionSortingDataBegin() {
        return insertionSortingDataBegin;
    }

    public void setInsertionSortingDataBegin(List<Integer> insertionSortingDataBegin) {
        this.insertionSortingDataBegin = insertionSortingDataBegin;
    }

    public List<Integer> getQuickSortingDataBegin() {
        return quickSortingDataBegin;
    }

    public void setQuickSortingDataBegin(List<Integer> quickSortingDataBegin) {
        this.quickSortingDataBegin = quickSortingDataBegin;
    }

    public long getQuickSortDuration() {
        return quickSortDuration;
    }

    public void setQuickSortDuration(long quickSortDuration) {
        this.quickSortDuration = quickSortDuration;
    }

    public long getInsertionSortDuration() {
        return insertionSortDuration;
    }

    public void setInsertionSortDuration(long insertionSortDuration) {
        this.insertionSortDuration = insertionSortDuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
