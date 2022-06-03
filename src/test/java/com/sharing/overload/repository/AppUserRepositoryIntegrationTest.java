package com.sharing.overload.repository;

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
public class AppUserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppUserRepository userRepository;

    @Test
    public void whenFindByName_thenReturnAppUser() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        entityManager.persist(bob);
        entityManager.flush();

        AppUser found = userRepository.findAppUserByUsername("bob");

        assertThat(found.getUsername()).isEqualTo(bob.getUsername());
    }

    @Test
    public void whenFindById_thenReturnAppUser() {
        AppUser bob = new AppUser(AppUser.Role.REGULAR_USER, "bob");
        entityManager.persist(bob);
        entityManager.flush();

        AppUser found = userRepository.findAppUserById(bob.getId());

        assertThat(found.getUsername()).isEqualTo(bob.getUsername());
    }
}
