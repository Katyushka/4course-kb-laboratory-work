package com.kb.service.parallelprogramming;

import com.kb.model.parallelprogramming.dto.SortingData;
import com.kb.util.SortUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortingThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(SortingThread.class);

    private SortingData sortingData;
    private String type;
    private boolean pause = false;

    public SortingThread(SortingData sortingData, String type) {
        this.sortingData = sortingData;
        this.type = type;
    }

    @Override
    public void run() {
        sortingData.setStartTime(System.currentTimeMillis());
        try {
            if ("qSort".equals(type)) {
                SortUtils.qSort(sortingData);
            } else {
                SortUtils.insertionSort(sortingData);
            }
        } catch (InterruptedException e) {
            log.error(ExceptionUtils.getMessage(e));
        }
        sortingData.setEndTime(System.currentTimeMillis());
        log.debug("Thread " + type + " stopped.");
    }

    @Override
    public synchronized void start() {
        log.debug("Thread " + type + " started.");
        super.start();
    }

    @Override
    public synchronized void interrupt() {
        log.error("Thread " + type + " interrupted.");
        super.interrupt();
    }


    public SortingData getSortingData() {
        return sortingData;
    }

    public String getType() {
        return type;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
