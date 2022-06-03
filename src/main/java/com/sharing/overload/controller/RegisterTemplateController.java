package com.sharing.overload.controller;

import com.sharing.overload.entity.SimpleUserData;
import com.sharing.overload.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterTemplateController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userData", new SimpleUserData());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegister(@ModelAttribute SimpleUserData userData) {
        userService.addNewRegularUser(userData.getUsername(), userData.getPassword());
        return "redirect:/";
    }
}
