package com.sharing.overload.controller;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.security.ActiveAppUsers;
import com.sharing.overload.security.AppLogoutSuccessHandler;
import com.sharing.overload.security.AppUrlAuthenticationSuccessHandler;
import com.sharing.overload.security.AppUserDetailsService;
import com.sharing.overload.service.AppUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestUserController.class)
public class RestUserControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppLogoutSuccessHandler appLogoutSuccessHandler;

    @MockBean
    private AppUrlAuthenticationSuccessHandler appUrlAuthenticationSuccessHandler;

    @MockBean
    private AppUserDetailsService appUserDetailsService;

    @MockBean
    private ActiveAppUsers activeAppUsers;

    @MockBean
    private AppUserService appUserService;

    @Test
    public void givenAppUsers_whenGetAppUsers_thenReturnJsonArray() throws Exception {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        AppUser alice = new AppUser(AppUser.Role.REGULAR_USER, "alice");
        AppUser kate = new AppUser(AppUser.Role.REGULAR_USER, "kate");

        List<AppUser> allUsers = Arrays.asList(bob, alice, kate);
        when(appUserService.getAllUsers()).thenReturn(allUsers);

        mvc.perform(get("/rest/users")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].username").value(bob.getUsername()))
                .andExpect(jsonPath("$[1].username").value(alice.getUsername()))
                .andExpect(jsonPath("$[2].username").value(kate.getUsername()));
    }
}
