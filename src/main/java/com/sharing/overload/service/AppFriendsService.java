package com.sharing.overload.service;

import com.sharing.overload.entity.AppFriends;
import com.sharing.overload.entity.AppUser;
import com.sharing.overload.repository.AppFriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppFriendsService {

    @Autowired
    private AppFriendsRepository repository;

    @Autowired
    private AppUserService userService;

    public void makeFriends(@NotNull AppUser firstUser, @NotNull AppUser secondUser) {
        repository.save(new AppFriends(firstUser.getId(), secondUser.getId()));
        repository.save(new AppFriends(secondUser.getId(), firstUser.getId()));
    }

    public void makeFriends(String firstUsername, String secondUsername) {
        AppUser firstUser = userService.findAppUserByUsername(firstUsername);
        AppUser secondUser = userService.findAppUserByUsername(secondUsername);

        repository.save(new AppFriends(firstUser.getId(), secondUser.getId()));
        repository.save(new AppFriends(secondUser.getId(), firstUser.getId()));
    }

    public List<AppUser> getFriends(String username) {
        AppUser user = userService.findAppUserByUsername(username);
        List<AppFriends> all = repository.findAll();
        List<AppUser> friends = new ArrayList<>();
        for (AppFriends f : all) {
            if (f.getUserId() == user.getId()) {
                friends.add(userService.findAppUserById(f.getFriendId()));
            }
        }

        return friends;
    }

    public AppFriends findAppFriendsByUserId(long id) {
        return repository.findAppFriendsByUserId(id);
    }

    public AppFriends findAppFriendsByFriendId(long id) {
        return repository.findAppFriendsByFriendId(id);
    }
}
