package com.sharing.overload.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_post_comments")
@NoArgsConstructor
@AllArgsConstructor
public class AppPostComment {

    @Id
    @GeneratedValue
    @Getter @Setter
    private long id;

    @Getter @Setter
    private long appPostId;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String content;

    public AppPostComment(long appPostId, String username, String content) {
        this.appPostId = appPostId;
        this.username = username;
        this.content = content;
    }
}
