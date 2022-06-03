package com.sharing.overload.controller;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppFriendsService;
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
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private AppFriendsService friendsService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{username}")
    public String getFriends(@PathVariable String username, Model model) {
        addCurrentUserToModel(model);

        List<AppUser> friends = friendsService.getFriends(username);
        model.addAttribute("friends", friends);
        return "friends";
    }

    private void addCurrentUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            AppUser appUser = appUserService.findByUsername(currentUserName);

            model.addAttribute("appUser", appUser);
        }
    }


    @GetMapping("/make-friends/{friend}")
    public String makeFriends(@PathVariable String friend) {
        String user = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            AppUser appUser = appUserService.findByUsername(currentUserName);
            user = appUser.getUsername();
        }

        friendsService.makeFriends(user, friend);
        return "redirect:/";
    }

}
