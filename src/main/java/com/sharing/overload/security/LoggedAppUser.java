package com.sharing.overload.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class LoggedAppUser implements HttpSessionBindingListener {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private ActiveAppUsers activeAppUsers;

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeAppUsers.getAppUsers();
        LoggedAppUser user = (LoggedAppUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeAppUsers.getAppUsers();
        LoggedAppUser user = (LoggedAppUser) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
    }
}
