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
        sortingDataPair.getQuickSortingData().setExpectedCount(Math.round(100000 * Math.log10(100000)));
        return sortingDataPair;
    }

    private List<Integer> generatingData(List<Integer> data) {
        for (int i = 0; i < 100000; i++) {
            Random rand = new Random();
            data.add(-10000 + rand.nextInt(20000));
        }
        return data;
    }

    public void doSort(SortingDataPair sortingDataPair) {
        qSort(sortingDataPair.getQuickSortingData());
        insertionSort(sortingDataPair.getInsertionSortingData());
    }

    private void qSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        sortingData.setThread(new SortingThread(sortingData, "qSort"));
        sortingData.getThread().start();
        log.debug("qSort duration: " + (System.currentTimeMillis() - t1));
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

    private void insertionSort(SortingData sortingData) {
        long t1 = System.currentTimeMillis();
        sortingData.setThread(new SortingThread(sortingData, "insertionSort"));
        sortingData.getThread().start();
        log.debug("insertionSort duration: " + (System.currentTimeMillis() - t1));
        sortingData.setDuration(System.currentTimeMillis() - t1);
    }

    public void pause(SortingDataPair sortingDataPair) {
        if (!sortingDataPair.getInsertionSortingData().getThread().isPause()) {
            sortingDataPair.getInsertionSortingData().getThread().setPause(true);
        } else {
            synchronized (sortingDataPair.getInsertionSortingData().getThread()) {
                sortingDataPair.getInsertionSortingData().getThread().notify();
            }
            sortingDataPair.getInsertionSortingData().getThread().setPause(false);
        }
        if (!sortingDataPair.getQuickSortingData().getThread().isPause()) {
            sortingDataPair.getQuickSortingData().getThread().setPause(true);
        } else {
            synchronized (sortingDataPair.getQuickSortingData().getThread()) {
                sortingDataPair.getQuickSortingData().getThread().notify();
            }
            sortingDataPair.getQuickSortingData().getThread().setPause(false);
        }
    }

}
