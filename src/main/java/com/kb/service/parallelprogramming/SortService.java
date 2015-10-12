package com.kb.service.parallelprogramming;

import com.kb.model.parallelprogramming.dto.SortingData;
import com.kb.model.parallelprogramming.dto.SortingDataPair;
import com.kb.model.parallelprogramming.dto.SortingForm;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class SortService {
    private static final Logger log = LoggerFactory.getLogger(SortService.class);

    public SortingDataPair generateData() {
        SortingDataPair sortingDataPair = new SortingDataPair();
        SortingData sortingData = new SortingData(generatingData(sortingDataPair.getQuickSortingData().getData()));
        sortingDataPair.setQuickSortingData(sortingData);
        sortingDataPair.setInsertionSortingData(new SortingData(new ArrayList<Integer>(sortingData.getData())));
        return sortingDataPair;
    }

    private List<Integer> generatingData(List<Integer> data) {
        for (int i = 0; i < 100000; i++) {
            Random rand = new Random();
            data.add(rand.nextInt(1000));
        }
        return data;
    }

    public void doSort(SortingDataPair sortingDataPair) {
        qSort(sortingDataPair.getQuickSortingData());
        insertionSort(sortingDataPair.getInsertionSortingData());
    }

    private void qSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        new SortingThread(sortingData, "qSort").start();
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

    private void insertionSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        new SortingThread(sortingData, "insertionSort").start();
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

}
