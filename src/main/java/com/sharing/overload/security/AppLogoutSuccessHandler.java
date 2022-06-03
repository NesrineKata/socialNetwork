package com.sharing.overload.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("appLogoutHandler")
public class AppLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response, Authentication authentication)
            throws IOException {
        HttpSession session = request.getSession();
        if(session == null)
            throw new IOException("Authentication session is null!");
        else {
            session.removeAttribute("user");
            response.sendRedirect("/login");
        }
    }
}
