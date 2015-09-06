package com.kb.web.controllers.parallelprogramming;

import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
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

@Controller
@RequestMapping("/pp")
public class ThreadsController {
    private static final Logger log = LoggerFactory.getLogger(ThreadsController.class);

    @Autowired
    private ThreadService threadService;

    @RequestMapping("/lab1")
    public String getIntegral(Model model) {
        model.addAttribute("midpointRuleForm", threadService.getDefaultMidpointRuleForm());
        return "parallelprogramming/threads";
    }

    @RequestMapping(value = "/lab1", method = RequestMethod.POST)
    public String calculateIntegral(@ModelAttribute("midpointRuleForm") MidpointRuleForm midpointRuleForm, BindingResult bindingResult, Model model) {
        threadService.calculateMidpointRuleIntegral(midpointRuleForm);
        return "parallelprogramming/threads";
    }
}
