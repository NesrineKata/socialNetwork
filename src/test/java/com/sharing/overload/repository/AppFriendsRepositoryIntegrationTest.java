package com.sharing.overload.repository;

import com.sharing.overload.entity.AppFriends;
import com.sharing.overload.entity.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppFriendsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppFriendsRepository friendsRepository;

    @Test
    public void whenFindByUserId_theReturnAppFriends() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        AppUser alice = new AppUser(AppUser.Role.REGULAR_USER, "alice");

        AppFriends friends = new AppFriends(bob.getId(), alice.getId());

        entityManager.persist(friends);
        entityManager.flush();

        AppFriends found = friendsRepository.findAppFriendsByUserId(bob.getId());
        assertThat(bob.getId()).isEqualTo(found.getUserId());
        assertThat(alice.getId()).isEqualTo(found.getFriendId());
    }

    @Test
    public void whenFindByFriendId_thenReturnAppFriends() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        AppUser alice = new AppUser(AppUser.Role.REGULAR_USER, "alice");

        AppFriends friends = new AppFriends(bob.getId(), alice.getId());

        entityManager.persist(friends);
        entityManager.flush();

        AppFriends found = friendsRepository.findAppFriendsByFriendId(alice.getId());
        assertThat(bob.getId()).isEqualTo(found.getUserId());
        assertThat(alice.getId()).isEqualTo(found.getFriendId());
    }
}
