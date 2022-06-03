package com.sharing.overload.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveAppUsers {

    @Getter @Setter
    private List<String> appUsers;

    public ActiveAppUsers() {
        appUsers = new ArrayList<>();
    }
}
