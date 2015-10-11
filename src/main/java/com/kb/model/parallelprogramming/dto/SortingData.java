package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chernov Artur on 11.10.15.
 */
public class SortingData implements Serializable{
    private static final long serialVersionUID = -5848727080566986560L;

    private long duration = 0;
    private List<Integer> data = new ArrayList<>();

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
}
