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
    private long quickSortStatus = 0;
    private long insertionSortStatus = 0;
    private boolean complete = false;


    public SortingForm() {
    }

    public SortingForm(SortingDataPair sortingDataPair) {
        quickSortingDataBegin = new ArrayList<>(sortingDataPair.getQuickSortingData().getData().subList(0, 1000));
        insertionSortingDataBegin = new ArrayList<>(sortingDataPair.getInsertionSortingData().getData().subList(0, 1000));
        quickSortDuration = (sortingDataPair.getQuickSortingData().getEndTime() == 0) ?
                System.currentTimeMillis() - (sortingDataPair.getQuickSortingData().getStartTime() == 0 ? System.currentTimeMillis() : sortingDataPair.getQuickSortingData().getStartTime()) :
                sortingDataPair.getQuickSortingData().getEndTime() -
                        (sortingDataPair.getQuickSortingData().getStartTime() == 0 ? System.currentTimeMillis() : sortingDataPair.getQuickSortingData().getStartTime());
        insertionSortDuration = (sortingDataPair.getInsertionSortingData().getEndTime() == 0) ?
                System.currentTimeMillis() - (sortingDataPair.getInsertionSortingData().getStartTime() == 0 ? System.currentTimeMillis() : sortingDataPair.getInsertionSortingData().getStartTime()) :
                sortingDataPair.getInsertionSortingData().getEndTime() -
                        (sortingDataPair.getInsertionSortingData().getStartTime() == 0 ? System.currentTimeMillis() : sortingDataPair.getInsertionSortingData().getStartTime());
        complete = (sortingDataPair.getQuickSortingData().getEndTime() != 0 && sortingDataPair.getInsertionSortingData().getEndTime() != 0);
        if (sortingDataPair.getQuickSortingData().getEndTime() != 0) {
            quickSortStatus = 100;
        } else {
            quickSortStatus = sortingDataPair.getQuickSortingData().getCurrentCount() * 100 / sortingDataPair.getQuickSortingData().getExpectedCount();
        }
        if (sortingDataPair.getInsertionSortingData().getEndTime() != 0) {
            insertionSortStatus = 100;
        } else {
            insertionSortStatus = sortingDataPair.getInsertionSortingData().getStatus();
        }
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

    public long getQuickSortStatus() {
        return quickSortStatus;
    }

    public void setQuickSortStatus(long quickSortStatus) {
        this.quickSortStatus = quickSortStatus;
    }

    public long getInsertionSortStatus() {
        return insertionSortStatus;
    }

    public void setInsertionSortStatus(long insertionSortStatus) {
        this.insertionSortStatus = insertionSortStatus;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
