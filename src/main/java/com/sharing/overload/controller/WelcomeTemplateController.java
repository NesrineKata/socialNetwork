package com.sharing.overload.controller;

import com.sharing.overload.entity.AppPost;
import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppPostService;
import com.sharing.overload.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class WelcomeTemplateController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeTemplateController.class);

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppPostService appPostService;

    @GetMapping("/")
    public String welcome(Model model) {
        addCurrentUserToModel(model);
        addPostToModel(model);
        return "welcome";
    }

    private void addCurrentUserToModel(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            AppUser appUser = appUserService.findByUsername(currentUserName);

            model.addAttribute("appUser", appUser);
        }
    }

    private void addPostToModel(Model model) {
        model.addAttribute("appPost", new AppPost());
    }

    @PostMapping("/submit-post")
    public String homePostSubmit(@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute AppPost appPost) {
        saveAppPost(file, appPost);
        return "redirect:/";
    }

    private void saveAppPost(MultipartFile file, AppPost appPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String currentUserName = authentication.getName();
            appPost.setUsername(currentUserName);
            appPost.setUnixTime(System.currentTimeMillis() / 1000L);

            if (isFileFetched(file)) {
                setImageToAppPost(appPost, file);
            }
        }

        appPostService.save(appPost);
    }

    private boolean isFileFetched(MultipartFile file) {
        return (file.getOriginalFilename() != null && !file.getOriginalFilename().trim().equals(""));
    }

    private void setImageToAppPost(AppPost appPost, MultipartFile file) {
        try {
            appPost.setImage(file.getBytes());
            logger.info("New file saved : " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
