package com.sharing.overload.service;

import com.sharing.overload.entity.AppPost;
import com.sharing.overload.repository.AppPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AppPostServiceIntegrationTest {

    @Mock
    private AppPostRepository appPostRepository;

    @InjectMocks
    private AppPostService appPostService;

    @Test
    public void whenFindByUsername_thenReturnPostsCount() {
        AppPost post = new AppPost(12, "bob", "lubie placki", System.currentTimeMillis() / 1000L);
        AppPost secondPost = new AppPost(12, "bob", "jednak nie lubie plackow", System.currentTimeMillis() / 1000L);
        AppPost thirdPost = new AppPost(12, "bob", "albo i lubie", System.currentTimeMillis() / 1000L);

        when(appPostRepository.findAppPostByUsername("bob")).thenReturn(Arrays.asList(post, secondPost, thirdPost));

        List<AppPost> found = appPostService.findAppPostByUsername("bob");
        assertThat(found.size()).isEqualTo(3);
    }

    @Test
    public void whenFindById_thenReturnAppPostContent() {
        AppPost post = new AppPost(12, "bob", "lubie placki", System.currentTimeMillis() / 1000L);

        when(appPostRepository.findAppPostById(post.getId())).thenReturn(post);

        AppPost found = appPostRepository.findAppPostById(post.getId());
        assertThat(post.getContent()).isEqualTo(found.getContent());
    }
}
