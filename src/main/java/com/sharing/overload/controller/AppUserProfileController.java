package com.sharing.overload.controller;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class AppUserProfileController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username, Model model) {
        addCurrentUserToModel(model);

        AppUser appUser = appUserService.findAppUserByUsername(username);
        model.addAttribute("appUserProfile", appUser);
        return "profile";
    }


    private void addCurrentUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            AppUser appUser = appUserService.findByUsername(currentUserName);

            model.addAttribute("appUser", appUser);
        }
    }


}
