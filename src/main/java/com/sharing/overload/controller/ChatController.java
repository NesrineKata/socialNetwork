package com.sharing.overload.controller;

import com.sharing.overload.entity.AppChatMessage;
import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private AppUserService appUserService;

    @MessageMapping("/ws-chat")
    @SendTo("/topic/messages")
    public AppChatMessage getChat(AppChatMessage chatMessage) {
        return new AppChatMessage(chatMessage.getValue());
    }

    @GetMapping("/chat")
    public String getChatTemplate(Model model) {
        addCurrentUserToModel(model);
        return "chat";
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
