package com.kb.web.controllers.cryptography;

import com.kb.model.cryptography.dto.EntropyForm;
import com.kb.model.parallelprogramming.dto.MidpointRuleForm;
import com.kb.service.cryptography.EntropyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 15.09.15.
 */
@Controller
@RequestMapping("/crypt")
public class EntropyController {
    private static final Logger log = LoggerFactory.getLogger(EntropyController.class);

    @Autowired
    private EntropyService entropyService;

    @RequestMapping(value = "/lab1", method = RequestMethod.GET)
    public String getIntegral(Model model) {
        EntropyForm form = new EntropyForm();

        form.getC().add(Arrays.asList("3", "4", "2", "1"));
        form.getC().add(Arrays.asList("3", "1", "4", "2"));
        form.getC().add(Arrays.asList("4", "3", "1", "2"));

        form.setpM(Arrays.asList(0.25, 0.3, 0.15, 0.3));

        form.setpK(Arrays.asList(0.25, 0.5, 0.25));
        model.addAttribute("form", form);
        return "cryptography/lab1";
    }

    @RequestMapping(value = "/lab1", method = RequestMethod.POST, params = {"build"})
    public String build(@ModelAttribute("form") EntropyForm form, BindingResult bindingResult, Model model) {
        for (int i = 0; i < form.getK(); i++) {
            List<String> list = new ArrayList<>();
            form.getC().add(list);
            for (int j = 0; j < form.getM(); j++) {
                list.add("0");
            }
        }
        for (int i = 0; i < form.getM(); i++) {
            form.getpM().add(0.0);
        }
        for (int i = 0; i < form.getK(); i++) {
            form.getpK().add(0.0);
        }

        return "cryptography/lab1";
    }


    @RequestMapping(value = "/lab1", method = RequestMethod.POST, params = {"generate"})
    public String generate(@ModelAttribute("form") EntropyForm form, BindingResult bindingResult, Model model) {
        form = entropyService.calculateProbability(form);
        return "cryptography/lab1";
    }


}
