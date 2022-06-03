package com.sharing.overload.controller;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{searchQuery}")
    public String getSearchQueries(@PathVariable String searchQuery, Model model) {

        addCurrentUserToModel(model);

        List<AppUser> foundUsers = appUserService.findAppUserContaining(searchQuery);
        model.addAttribute("foundUsers", foundUsers);

        return "search";
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
