package com.kb.service.parallelprogramming;


import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
import com.kb.util.Integrals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public MidpointRuleForm calculateMidpointRuleIntegral(MidpointRuleForm midpointRuleForm) {
        midpointRuleForm.setResult(calculateIntegral(midpointRuleForm.getFunction(), midpointRuleForm.getLeft(), midpointRuleForm.getRight(),
                (midpointRuleForm.getRight() - midpointRuleForm.getLeft()) / midpointRuleForm.getStepsCount()));
        return midpointRuleForm;
    }

    private double calculateIntegral(String function, double left, double right, double step) {
        return Integrals.midPointRule(function, left, right, step);
    }
}
