package com.sharing.overload.controller;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.entity.SimpleUserData;
import com.sharing.overload.security.ActiveAppUsers;
import com.sharing.overload.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class RestUserController {

    @Autowired
    private AppUserService service;

    @Autowired
    private ActiveAppUsers activeAppUsers;


    @GetMapping
    public List<AppUser> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/new-regular")
    public void addNewRegularUser(@RequestBody SimpleUserData simpleUserData) {
        service.addNewRegularUser(simpleUserData.getUsername(), simpleUserData.getPassword());
    }

    @GetMapping("/logged-in")
    public Collection<String> getLoggedUsers() {
        return activeAppUsers.getAppUsers();
    }
}
