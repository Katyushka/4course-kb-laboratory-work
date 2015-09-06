package com.kb.util;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Integrals {
    private static final String CALCULATE_MIDPOINT_RULE_FUNCTION = "var getIntegral = function(x) " +
            "{" + System.lineSeparator() + " return ${script}" + System.lineSeparator() + "};";
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public static double midPointRule(String function, double left, double right, double step) {
        double integral = 0;
        function = CALCULATE_MIDPOINT_RULE_FUNCTION.replace("${script}", function);
        for (double i = left; i < right; i += step) {
            integral += calculateSquare(function, i, i + step);
        }
        return integral;
    }

    private static double calculateSquare(String function, double left, double right) {
        return calculateFunction((left + right) / 2, function) * (right - left);
    }

    private static double calculateFunction(double x, String function) {
        Double resultVal = 0d;
        try {
            Object result = invokeMidpointRuleIntegralFunction(function, x);
            resultVal = (Double) result;
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
            return resultVal;
        }
        return resultVal;
    }

    private static Object invokeMidpointRuleIntegralFunction(String function, double x) throws ScriptException, NoSuchMethodException {
        engine.eval(function);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction("getIntegral", x);
    }

}
