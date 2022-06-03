package com.sharing.overload.service;

import com.sharing.overload.entity.AppFriends;
import com.sharing.overload.entity.AppUser;
import com.sharing.overload.repository.AppFriendsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AppFriendsServiceIntegrationTest {

    @Mock
    private AppFriendsRepository appFriendsRepository;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppFriendsService appFriendsService;

    @Test
    public void whenFindByUserId_thenReturnUserFriends() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        AppUser alice = new AppUser(AppUser.Role.REGULAR_USER, "alice");
        AppFriends friends = new AppFriends(bob.getId(), alice.getId());

        when(appFriendsRepository.findAppFriendsByUserId(bob.getId()))
                .thenReturn(friends);


        AppFriends found = appFriendsService.findAppFriendsByUserId(bob.getId());

        assertThat(found.getUserId()).isEqualTo(bob.getId());
        assertThat(found.getFriendId()).isEqualTo(alice.getId());
    }

    @Test
    public void whenFindByFriendId_thenReturnUserFriends() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        AppUser alice = new AppUser(AppUser.Role.REGULAR_USER, "alice");
        AppFriends friends = new AppFriends(bob.getId(), alice.getId());

        when(appFriendsRepository.findAppFriendsByFriendId(alice.getId()))
                .thenReturn(friends);

        AppFriends found = appFriendsService.findAppFriendsByFriendId(alice.getId());

        assertThat(found.getUserId()).isEqualTo(bob.getId());
        assertThat(found.getFriendId()).isEqualTo(alice.getId());
    }
}
