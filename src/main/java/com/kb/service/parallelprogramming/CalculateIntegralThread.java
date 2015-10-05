package com.kb.service.parallelprogramming;

import com.kb.util.Integrals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculateIntegralThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(ThreadService.class);

    private String function;
    private double left;
    private double right;
    private double step;
    private double result;
    private int index;

    public CalculateIntegralThread(String function, double left, double right, double step, int index) {
        this.function = function;
        this.left = left;
        this.right = right;
        this.step = step;
        this.index = index + 1;
    }

    @Override
    public void run() {
        result = Integrals.midPointRule(function, left, right, step);
        log.error("Thread " + index + " stopped.");
    }

    @Override
    public synchronized void start() {
        log.error("Thread " + index + " started.");
        super.start();
    }

    public double getResult() {
        return result;
    }

    public int getIndex() {
        return index;
    }
}
