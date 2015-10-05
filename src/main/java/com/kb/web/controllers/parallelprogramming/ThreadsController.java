package com.kb.web.controllers.parallelprogramming;

import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
import com.kb.model.parallelprogramming.dto.SortingForm;
import com.kb.service.parallelprogramming.CalculateIntegralThread;
import com.kb.service.parallelprogramming.SortService;
import com.kb.service.parallelprogramming.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pp")
public class ThreadsController {
    private static final Logger log = LoggerFactory.getLogger(ThreadsController.class);

    private static final int POOL_SIZE = 3;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private SortService sortService;

    private List<CalculateIntegralThread> threadPool = new ArrayList<>();

    @PostConstruct
    private void init() {
    }

    @RequestMapping("/lab1")
    public String getIntegral(Model model) {
        model.addAttribute("midpointRuleForm", threadService.getDefaultMidpointRuleForm());
        return "parallelprogramming/threads";
    }

    @RequestMapping(value = "/lab1", method = RequestMethod.POST)
    public String calculateIntegral(@ModelAttribute("midpointRuleForm") MidpointRuleForm midpointRuleForm, BindingResult bindingResult, Model model) {
        threadService.calculateMidpointRuleIntegral(midpointRuleForm, threadPool, POOL_SIZE);
        return "parallelprogramming/threads";
    }

    @RequestMapping("/lab1_1")
    public String getData(Model model) {
        model.addAttribute("sortingForm", sortService.getDefaultSortingForm());
        return "parallelprogramming/threads";
    }

    @RequestMapping(value = "/lab1_1", method = RequestMethod.POST)
    public String generateData(@ModelAttribute("sortingForm") SortingForm sortingForm, BindingResult bindingResult, Model model) {
        sortingForm = sortService.generateData(sortingForm);
        return "parallelprogramming/threads";
    }


}
