package com.kb.service.parallelprogramming;

import com.kb.model.parallelprogramming.dto.SortingData;
import com.kb.util.Integrals;
import com.kb.util.SortUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortingThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SortingThread.class);

    private SortingData sortingData;
    private String type;

    public SortingThread(SortingData sortingData, String type) {
        this.sortingData = sortingData;
        this.type = type;
    }

    @Override
    public void run() {
        if ("qSort".equals(type)) {
            SortUtils.qSort(sortingData.getData());
        } else {
            SortUtils.insertionSort(sortingData.getData());
        }
        log.error("Thread " + type + " stopped.");
    }

    @Override
    public synchronized void start() {
        log.error("Thread " + type + " started.");
        super.start();
    }
}
