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
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/lab1/integral")
    public String getIntegral(Model model) {
        model.addAttribute("midpointRuleForm", threadService.getDefaultMidpointRuleForm());
        return "parallelprogramming/integral";
    }

    @RequestMapping(value = "/lab1/integral", method = RequestMethod.POST)
    public String calculateIntegral(@ModelAttribute("midpointRuleForm") MidpointRuleForm midpointRuleForm, BindingResult bindingResult, Model model) {
        threadService.calculateMidpointRuleIntegral(midpointRuleForm, threadPool, POOL_SIZE);
        return "parallelprogramming/integral";
    }

    // Task 2

    @RequestMapping("/lab1/sorting")
    public String getData(Model model) {
        model.addAttribute("sortingForm", sortService.getDefaultSortingForm());
        return "parallelprogramming/sorting";
    }

    @RequestMapping(value = "/lab1/sorting", method = RequestMethod.POST, params = "generate")
    public String generateData(@ModelAttribute("sortingForm") SortingForm sortingForm, BindingResult bindingResult, Model model) {
        sortService.generateData(sortingForm);
        return "parallelprogramming/sorting";
    }

    @RequestMapping(value = "/lab1/sorting", method = RequestMethod.POST)
    public String sorting(@ModelAttribute("sortingForm") SortingForm sortingForm, BindingResult bindingResult, Model model) {
        sortService.doSort(sortingForm);
        return "parallelprogramming/sorting";
    }

    @ResponseBody
    @RequestMapping(value = "/lab1/sorting/status", method = RequestMethod.POST)
    public Long getStatus(@ModelAttribute("sortingForm") SortingForm sortingForm, @RequestParam("type") String type) {
        return sortingForm.getQuickSortingData().getStatus();
    }


}
