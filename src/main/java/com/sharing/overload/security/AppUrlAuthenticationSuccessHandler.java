package com.sharing.overload.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("appAuthenticationHandler")
public class AppUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(AppUrlAuthenticationSuccessHandler.class);

    @Autowired
    private ActiveAppUsers activeAppUsers;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        logger.info("success");

        HttpSession session = request.getSession(false);
        if (session == null)
            throw new IOException("Authentication session is null!");

        else {
            LoggedAppUser user = new LoggedAppUser(authentication.getName(), activeAppUsers);
            session.setAttribute("user", user);

            response.sendRedirect("/");
        }

    }
}
