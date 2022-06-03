package com.sharing.overload.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_posts")
@JsonIgnoreProperties(ignoreUnknown = true)

@NoArgsConstructor
@AllArgsConstructor
public class AppPost {

    @Id
    @GeneratedValue
    @Getter @Setter
    private long id;

    @Getter @Setter
    private long boardId;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String content;

    @Lob
    @Getter @Setter
    private byte[] image;

    @Getter @Setter
    private long unixTime;

    public AppPost(long boardId, String username, String content, long unixTime) {
        this.boardId = boardId;
        this.username = username;
        this.content = content;
        this.unixTime = unixTime;
    }
}
