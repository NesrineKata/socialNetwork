package com.sharing.overload.repository;

import com.sharing.overload.entity.AppFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppFriendsRepository extends JpaRepository<AppFriends, Long> {
    AppFriends findAppFriendsByUserId(long id);
    AppFriends findAppFriendsByFriendId(long id);
}
