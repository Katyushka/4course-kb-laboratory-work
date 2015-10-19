package com.kb.web.controllers.parallelprogramming;

import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
import com.kb.model.parallelprogramming.dto.SortingDataPair;
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

    private SortingDataPair sortingDataPair;

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
        sortingDataPair = sortService.generateData();
        model.addAttribute("sortingForm", new SortingForm(sortingDataPair));
        return "parallelprogramming/sorting";
    }

    @RequestMapping(value = "/lab1/sorting", method = RequestMethod.POST, params = "generate")
    public String generateData(Model model) {
        sortingDataPair = sortService.generateData();
        model.addAttribute("sortingForm", new SortingForm(sortingDataPair));
        return "parallelprogramming/sorting";
    }

    @RequestMapping(value = "/lab1/sorting", method = RequestMethod.POST)
    public String sorting(Model model) {
        sortService.doSort(sortingDataPair);
        model.addAttribute("sortingForm", new SortingForm(sortingDataPair));
        return "parallelprogramming/sorting";
    }

    @ResponseBody
    @RequestMapping(value = "/lab1/sorting/action", method = RequestMethod.POST)
    public SortingForm getStatus(@RequestParam("type") String type) {
        if ("start".equals(type)) {
            log.debug("fire sorting start");
            sortService.doSort(sortingDataPair);
        } else if ("pause".equals(type)) {
            log.debug("fire pause/resume action");
            sortService.pause(sortingDataPair);
        }
        return new SortingForm(sortingDataPair);
    }


}
