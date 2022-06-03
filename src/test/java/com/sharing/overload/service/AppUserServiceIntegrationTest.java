package com.sharing.overload.service;

import com.sharing.overload.entity.AppUser;
import com.sharing.overload.repository.AppUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AppUserServiceIntegrationTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private AppUserService appUserService;

    @Test
    public void whenValidUsername_thenAppUserShouldBeFound() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");

        when(appUserRepository.findAppUserByUsername(bob.getUsername()))
                .thenReturn(bob);

        AppUser found = appUserService.findByUsername("bob");

        assertThat(bob.getUsername()).isEqualTo(found.getUsername());
    }
}
