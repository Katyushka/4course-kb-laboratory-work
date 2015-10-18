package com.kb.model.parallelprogramming.dto;

import com.kb.service.parallelprogramming.SortingThread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortingData implements Serializable {
    private static final long serialVersionUID = -5848727080566986560L;

    private SortingThread thread;
    private long duration = 0;
    private List<Integer> data = new ArrayList<>();
    private long status = 0;
    private long startTime = 0;
    private long endTime = 0;
    private long expectedCount = 0;
    private long currentCount = 0;


    public SortingData() {
    }

    public SortingData(List<Integer> data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public SortingThread getThread() {
        return thread;
    }

    public void setThread(SortingThread thread) {
        this.thread = thread;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getExpectedCount() {
        return expectedCount;
    }

    public void setExpectedCount(long expectedCount) {
        this.expectedCount = expectedCount;
    }

    public long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(long currentCount) {
        this.currentCount = currentCount;
    }
}
