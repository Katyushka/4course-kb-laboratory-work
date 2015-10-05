package com.kb.service.parallelprogramming;


import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
import com.kb.util.Integrals;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {
    private static final Logger log = LoggerFactory.getLogger(ThreadService.class);

    private static final String DEFAULT_MIDPOINT_RULE_FUNCTION = "15 * Math.cos(2*x)/Math.log(x)";
    private static final double DEFAULT_MIDPOINT_RULE_LEFT = 2;
    private static final double DEFAULT_MIDPOINT_RULE_RIGHT = 8;
    private static final int DEFAULT_MIDPOINT_RULE_COUNTS = 10000;

    public MidpointRuleForm getDefaultMidpointRuleForm() {
        MidpointRuleForm midpointRuleForm = new MidpointRuleForm();
        midpointRuleForm.setFunction(DEFAULT_MIDPOINT_RULE_FUNCTION);
        midpointRuleForm.setLeft(DEFAULT_MIDPOINT_RULE_LEFT);
        midpointRuleForm.setRight(DEFAULT_MIDPOINT_RULE_RIGHT);
        midpointRuleForm.setStepsCount(DEFAULT_MIDPOINT_RULE_COUNTS);
        return midpointRuleForm;
    }

    public MidpointRuleForm calculateMidpointRuleIntegral(MidpointRuleForm midpointRuleForm, List<CalculateIntegralThread> pool, int poolSize) {
        pool.clear();
        for (int i = 0; i < poolSize; i++) {
            CalculateIntegralThread thread = new CalculateIntegralThread(midpointRuleForm.getFunction(), midpointRuleForm.getLeft(i, poolSize), midpointRuleForm.getRight(i, poolSize),
                    (midpointRuleForm.getRight() - midpointRuleForm.getLeft()) / midpointRuleForm.getStepsCount(), i);
            thread.setPriority(midpointRuleForm.getThreadPriorities().get(i));
            pool.add(thread);
        }
        double result = 0;
        try {
            if (midpointRuleForm.isParallel()) {
                result = getParallelCalculation(pool);
            } else {
                result = getConsistencyCalculation(pool);
            }
        } catch (InterruptedException e) {
            log.debug(ExceptionUtils.getMessage(e));
        }
        midpointRuleForm.setResult(result);
        return midpointRuleForm;
    }

    private double getParallelCalculation(List<CalculateIntegralThread> pool) throws InterruptedException {
        double result = 0;
        for (CalculateIntegralThread thread : pool) {
            thread.start();
        }
        for (CalculateIntegralThread thread : pool) {
            thread.join();
        }
        for (CalculateIntegralThread thread : pool) {
            result += thread.getResult();
        }
        return result;
    }

    private double getConsistencyCalculation(List<CalculateIntegralThread> pool) throws InterruptedException {
        double result = 0;
        for (CalculateIntegralThread thread : pool) {
            thread.start();
            thread.join();
        }
        for (CalculateIntegralThread thread : pool) {
            result += thread.getResult();
        }
        return result;
    }

    private double calculateIntegral(String function, double left, double right, double step) {
        return Integrals.midPointRule(function, left, right, step);
    }
}
