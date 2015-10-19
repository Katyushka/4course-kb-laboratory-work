package com.kb.web.controllers.protectionProgramsAndData;

import com.kb.model.protectionProgramsAndData.KeePassForm;
import com.kb.service.protectionProgramsAndData.KeePassService;
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
 * Created by user on 19.10.15.
 */
@Controller
@RequestMapping("/protectPrograms")
public class KeePassController {
    private static final Logger log = LoggerFactory.getLogger(KeePassController.class);

    @Autowired
    private KeePassService keePassService;

    @RequestMapping(value = "/lab1", method = RequestMethod.GET)
    public String getIntegral(Model model) {
        KeePassForm form = new KeePassForm();
        form.setPassword("11111");
        form.setTitle("Title");
        form.setUserName("User");

        model.addAttribute("form", form);
        return "protectPrograms/lab1";
    }

    @RequestMapping(value = "/lab1", method = RequestMethod.POST, params = {"add"})
    public String build(@ModelAttribute("form") KeePassForm form, BindingResult bindingResult, Model model) {



        return "protectPrograms/lab1";
    }


}
