package com.kb.web.controllers.cryptography;

import com.kb.model.cryptography.dto.ShiftRegisterForm;
import com.kb.service.cryptography.ShiftRegisterService;
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
 * Created by user on 25.10.15.
 */
@Controller
@RequestMapping("/crypt")
public class ShiftRegisterController {
    private static final Logger log = LoggerFactory.getLogger(ShiftRegisterController.class);

    @Autowired
    private ShiftRegisterService shiftRegisterService;

    @RequestMapping(value = "/lab4", method = RequestMethod.GET)
    public String getInit(Model model){
        ShiftRegisterForm form = new ShiftRegisterForm();
        form.getInitialVector().add(0);
        form.getInitialVector().add(0);
        form.getInitialVector().add(0);

        model.addAttribute("form", form);
        return "cryptography/lab4";
    }



    @RequestMapping(value = "/lab4", method = RequestMethod.POST, params = {"build"})
    public String build(@ModelAttribute("form") ShiftRegisterForm form, BindingResult bindingResult, Model model) {

        List<Integer> tmp = new ArrayList<>();
        form.setInitialVector(tmp);
        for (int i =0; i<form.getN(); i++)
            form.getInitialVector().add(0);
        return "cryptography/lab4";
    }


    @RequestMapping(value = "/lab4", method = RequestMethod.POST, params = {"generate"})
    public String generate(@ModelAttribute("form") ShiftRegisterForm form, BindingResult bindingResult, Model model) {
        form = shiftRegisterService.generateSequance(form);
        return "cryptography/lab4";
    }

}
