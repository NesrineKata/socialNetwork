package com.sharing.overload.controller;

import com.sharing.overload.entity.AppPost;
import com.sharing.overload.entity.AppUser;
import com.sharing.overload.service.AppFriendsService;
import com.sharing.overload.service.AppPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/posts")
public class RestAppPostController {

    @Autowired
    private AppPostService appPostService;

    @Autowired
    private AppFriendsService appFriendsService;

    @GetMapping("/{username}")
    public List<AppPost> getUserPosts(@PathVariable String username) {
        return appPostService.findAppPostByUsername(username);
    }

    @GetMapping("/friends/{username}")
    public List<AppPost> getUsersFriendsPosts(@PathVariable String username) {
        return getFriendsPosts(username);
    }

    @GetMapping("/friendsAndUser/{username}")
    public List<AppPost> getFriendsAndUserPosts(@PathVariable String username) {
        List<AppPost> result = getFriendsPosts(username);
        result.addAll(appPostService.findAppPostByUsername(username));
        return result;
    }

    private List<AppPost> getFriendsPosts(String username) {
        List<AppPost> result = new ArrayList<>();

        List<AppUser> friends = appFriendsService.getFriends(username);
        for (AppUser f : friends) {
            List<AppPost> posts = appPostService.findAppPostByUsername(f.getUsername());
            result.addAll(posts);
        }

        return result;
    }

    @GetMapping("/id/{id}")
    public AppPost getPostById(@PathVariable long id) {
        return appPostService.findAppPostById(id);
    }
}
