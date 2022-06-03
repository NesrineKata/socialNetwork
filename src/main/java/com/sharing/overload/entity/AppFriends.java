package com.sharing.overload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_user_friends")
@NoArgsConstructor
@AllArgsConstructor
public class AppFriends {

    @Id
    @GeneratedValue
    @Getter @Setter
    private long id;

    @Column(name = "user_id")
    @Getter @Setter
    private long userId;

    @Column(name = "friend_id")
    @Getter @Setter
    private long friendId;

    public AppFriends(long userId, long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
