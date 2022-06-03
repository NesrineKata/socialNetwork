package com.sharing.overload.repository;

import com.sharing.overload.entity.AppPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppPostRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppPostRepository postRepository;

    @Test
    public void whenFindByUsername_thenReturnPostsCount() {
        AppPost post = new AppPost(12, "bob", "lubie placki", System.currentTimeMillis() / 1000L);
        AppPost secondPost = new AppPost(12, "bob", "jednak nie lubie plackow", System.currentTimeMillis() / 1000L);
        AppPost thirdPost = new AppPost(12, "bob", "albo i lubie", System.currentTimeMillis() / 1000L);

        entityManager.persist(post);
        entityManager.persist(secondPost);
        entityManager.persist(thirdPost);
        entityManager.flush();

        List<AppPost> bobPosts = postRepository.findAppPostByUsername("bob");
        assertThat(bobPosts.size()).isEqualTo(3);
    }

    @Test
    public void whenFindById_thenReturnAppPostContent() {
        AppPost post = new AppPost(12, "bob", "lubie placki", System.currentTimeMillis() / 1000L);

        entityManager.persist(post);
        entityManager.flush();

        AppPost found = postRepository.findAppPostById(post.getId());
        assertThat(post.getContent()).isEqualTo(found.getContent());
    }
}
