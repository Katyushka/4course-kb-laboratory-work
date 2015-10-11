package com.kb.web.controllers.cryptography;

import com.kb.model.cryptography.dto.SpectraForm;
import com.kb.model.cryptography.dto.TableSpectraForm;
import com.kb.service.cryptography.SpectraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by user on 30.09.15.
 */
@Controller
@RequestMapping("/crypt")
public class SpectraController {
    private static final Logger log = LoggerFactory.getLogger(SpectraController.class);
    @Autowired
    private SpectraService spectraService;



    @RequestMapping(value = "/lab2", method = RequestMethod.GET)
    public String getIntegral(Model model) {
        SpectraForm form = new SpectraForm();
        form.setVectorBF("10111010");
        model.addAttribute("form", form);
        return "cryptography/lab2";
    }

    @RequestMapping(value = "/lab2", method = RequestMethod.POST, params = {"generate"})
    public String build(@ModelAttribute("form") SpectraForm form, BindingResult bindingResult, Model model) {
        form = spectraService.calculateSpectrumFourier(form);
        return "cryptography/lab2";
    }

    @RequestMapping(value = "/lab3", method = RequestMethod.GET)
    public String generatingTable(Model model) {
        TableSpectraForm form = new TableSpectraForm();
        model.addAttribute("form", form);
        spectraService.calculatingTable(form);
        return "cryptography/lab3";
    }

}
