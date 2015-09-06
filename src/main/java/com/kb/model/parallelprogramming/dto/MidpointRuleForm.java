package com.kb.model.parallelprogramming.dto;

import java.io.Serializable;

public class MidpointRuleForm implements Serializable {
    private static final long serialVersionUID = 29487974483160874L;

    private int stepsCount;
    private double left;
    private double right;
    private String function;
    private double result = 0;

    public MidpointRuleForm() {
    }

    public MidpointRuleForm(int stepsCount, double left, double right, String function) {
        this.stepsCount = stepsCount;
        this.left = left;
        this.right = right;
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(int stepsCount) {
        this.stepsCount = stepsCount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
